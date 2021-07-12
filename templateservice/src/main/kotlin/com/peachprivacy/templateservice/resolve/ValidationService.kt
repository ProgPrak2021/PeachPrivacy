package com.peachprivacy.templateservice.resolve

/*@Service
class ValidationService(
    @Autowired private val jsonSchemaApi: MedeiaJacksonApi  // TODO actually needed?
) {
    // Private method for unknown reason
    private val jsonSchemaGetMethod = MedeiaApiBase::class.java.getDeclaredMethod(
        "loadSchema",
        SchemaSource::class.java,
        ValidationOptions::class.java,
        MutableMap::class.java
    ).also {
        it.isAccessible = true
    }

    fun mergeResolvedJsonPointers(mergeSubjects: List<Map<String, HandledConflict<Any?>>>): Map<String, HandledConflict<Any?>> {
        val conflictsAcc = mergeSubjects.firstOrNull()?.toMutableMap() ?: return emptyMap()
        mergeSubjects.drop(1).forEach { newMapIt ->
            newMapIt.forEach { (newPath, newConflict) ->
                conflictsAcc[newPath]?.let { preConflict ->
                    if (preConflict is UnresolvedConflict && preConflict.selfCandidates.isEmpty()) return@let null

                    val finalMergeStrategy = when (preConflict.merge) {
                        newConflict.merge -> preConflict.merge
                        SelfMergingStrategy.ALL, SelfMergingStrategy.SINGLE -> {
                            if (newConflict.merge != SelfMergingStrategy.ALL && newConflict.merge != SelfMergingStrategy.SINGLE)
                                SelfMergingStrategy.NONE
                            else
                                // Allowing merging of multiple arrays but then specifying that only one variant is valid
                                SelfMergingStrategy.SINGLE
                        }
                        else -> SelfMergingStrategy.NONE
                    }
                    val mergedConflict = when (finalMergeStrategy) {
                        SelfMergingStrategy.NONE -> {
                            val conflictCandidates =
                                (preConflict.selfCandidates + newConflict.selfCandidates).toMutableList()

                            if (conflictCandidates.size == 1)
                                ResolvedConflict(conflictCandidates, SelfMergingStrategy.NONE, conflictCandidates.first())
                            else
                                UnresolvedConflict(conflictCandidates)
                        }
                        SelfMergingStrategy.SINGLE -> {
                            // type Set so that the order doesn't matter. Is that correct though?
                            val conflictCandidates =
                                (preConflict.selfCandidates.toSet() + newConflict.selfCandidates.toSet()).distinct().toMutableList()

                            if (conflictCandidates.size == 1)
                                ResolvedConflict(conflictCandidates, SelfMergingStrategy.SINGLE, conflictCandidates.first())
                            else
                                UnresolvedConflict(conflictCandidates)
                        }
                        SelfMergingStrategy.ALL -> {
                            // This condition is only fulfilled if both conflicts have the ALL merge strategy. no check required
                            // Should be distinct? Seems logical.
                            val conflictCandidates =
                                (preConflict.selfCandidates + newConflict.selfCandidates).distinct().toMutableList()

                            // TODO: Create extra ResolvedConflict type for collections. This is plain wrong (for parameter value)
                            ResolvedConflict(conflictCandidates, SelfMergingStrategy.ALL, conflictCandidates)
                        }
                    }
                    conflictsAcc[newPath] = mergedConflict
                } ?: newConflict.let { conflictsAcc[newPath] = it }
            }
        }
        return conflictsAcc
    }

    fun getResolvedObject(schema: String): Map<String, Any?>? = getResolvedObject(getResolvedJsonPointers(schema))

    fun getResolvedObject(resolvedPointers: Map<String, HandledConflict<*>>): Map<String, Any?>? {
        val result = mutableMapOf<String, Any?>()

        val fullyResolved = resolvedPointers.none { (pointer, conflict) ->
            if (conflict !is ResolvedConflict) return@none true

            // https://github.com/json-patch/json-patch-tests/blob/master/spec_tests.json#L200
            // tl;dr: ~0 => ~, ~1 = / - Escape character
            val path = pointer.split("/").drop(1).map {
                it.replace("~1", "/").replace("~0", "~")
            }.filterIndexed { index, _ -> index % 2 == 1 }  // TODO Remove with new method

            // TODO Can a constant be defined for an object on the lowest level? Theoretically, maybe, yes. Currently invalid
            if (path.isEmpty()) return@none true

            val selectionPath = path.dropLast(1)
            val valueSelect = path.last()

            val resultSubSelection = selectionPath.fold(result) { currentSelection, pathElement ->
                val subSelection = currentSelection.computeIfAbsent(pathElement) { mutableMapOf<String, Any?>() }

                // Cannot check erased generic argument types
                if (subSelection !is MutableMap<*, *>) return@none true

                subSelection as MutableMap<String, Any?>
            }

            // TODO: Not safe - null value might be actual value!
            // Maps might be the actual final constant. TODO Maybe not a bug, think through it
            if (resultSubSelection.containsKey(valueSelect)) return@none true

            resultSubSelection[valueSelect] = conflict.value
            return@none false
        }

        if (!fullyResolved) return null

        return result
    }

    fun getResolvedJsonPointers(schema: String): Map<String, HandledConflict<Any?>> {
        val jsonSchema = jsonSchemaGetMethod.invoke(jsonSchemaApi,
            StringSchemaSource(schema),
            ValidationOptions.DEFAULT,
            mutableMapOf<URI, Any>()
        ) as Schema

        if (jsonSchema !is JsonSchema)
            // With base URI could go right if parsed correctly - (Json pointers in JsonSchema)
            throw NotImplementedError("Given schemas NEED to be pure JsonSchemas in nature at the moment")

        return getResolvedJsonPointers(jsonSchema)
    }

    fun getResolvedJsonPointers(schema: JsonSchema): Map<String, HandledConflict<Any?>> {
        val resolvedFields = mutableMapOf<String, MutableList<Any?>>()

        forAllSubSchemas(schema) {
            if (type?.contains(SimpleType.OBJECT) == true) return@forAllSubSchemas

            val candidates = resolvedFields.computeIfAbsent(jsonPointer.text) { mutableListOf() }

            val resolveNode = const ?: return@forAllSubSchemas
            val resolvedValue = resolveNode.toString().let {
                if (type?.contains(SimpleType.STRING) == true) it.removeSurrounding("\"") else it
            } /*when (resolveNode) {
                is SimpleNode -> resolveNode.toString()
                is ObjectNode -> resolveNode.nodes  // TODO: Better sub-node handling (important!) (Update: Handled above now?)
                is ArrayNode -> resolveNode.nodes
            }*/

            candidates.add(resolvedValue)
        }

        return resolvedFields.mapValues { (_, valueCandidates) ->
            if (valueCandidates.none()) {
                return@mapValues UnresolvedConflict(valueCandidates)
            }

            val distinctValueCandidates = valueCandidates.toSet().toMutableList()
            if (distinctValueCandidates.size == 1) {
                return@mapValues ResolvedConflict(
                    distinctValueCandidates,
                    SelfMergingStrategy.SINGLE,
                    distinctValueCandidates.single()
                )
            }

            return@mapValues UnresolvedConflict(valueCandidates)
        }
    }

    fun forAllSubSchemas(schema: JsonSchema, callback: JsonSchema.() -> Unit) {
        val subSchemas = mutableListOf(schema)
        var subSchemaIndex = 0

        while (subSchemaIndex < subSchemas.size) {
            val subSchema = subSchemas[subSchemaIndex]
            callback(subSchema)

            /*
            // Does this work properly with JSON Pointers?
            // There are plenty of similar scenarios (Fields of type JsonSchema in JsonSchema).
            subSchema.allOf?.let { subSchemas.addAll(it) }
            subSchema.anyOf?.let { subSchemas.addAll(it) }
            subSchema.oneOf?.let { subSchemas.addAll(it) }
            */

            subSchema.properties?.values?.let { subSchemas.addAll(it) }

            subSchemaIndex++
        }
    }
}*/
