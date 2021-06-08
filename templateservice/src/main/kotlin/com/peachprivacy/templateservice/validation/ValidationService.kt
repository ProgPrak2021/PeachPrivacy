package com.peachprivacy.templateservice.validation

import com.worldturner.medeia.api.*
import com.worldturner.medeia.api.jackson.MedeiaJacksonApi
import com.worldturner.medeia.parser.ArrayNode
import com.worldturner.medeia.parser.ObjectNode
import com.worldturner.medeia.parser.SimpleNode
import com.worldturner.medeia.pointer.JsonPointer
import com.worldturner.medeia.schema.model.JsonSchema
import com.worldturner.medeia.schema.model.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.URI

@Service
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

    fun getResolvedObject(schema: String): Map<String, Any?>? {
        val resolvedPointers = getResolvedJsonPointers(schema)

        val result = mutableMapOf<String, Any?>()

        val fullyResolved = resolvedPointers.none { (pointer, conflict) ->
            if (conflict !is ResolvedConflict) return@none true

            // https://github.com/json-patch/json-patch-tests/blob/master/spec_tests.json#L200
            // tl;dr: ~0 => ~, ~1 = / - Escape character
            val path = pointer.text.split("/").drop(1).map {
                it.replace("~1", "/").replace("~0", "~")
            }

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

    fun getResolvedJsonPointers(schema: String): Map<JsonPointer, HandledConflict<Any?>> {
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

    fun getResolvedJsonPointers(schema: JsonSchema): Map<JsonPointer, HandledConflict<Any?>> {
        val resolvedFields = mutableMapOf<JsonPointer, MutableList<Any?>>()

        forAllSubSchemas(schema) {
            val candidates = resolvedFields.computeIfAbsent(jsonPointer) { mutableListOf() }

            val resolveNode = const ?: return@forAllSubSchemas
            val resolvedValue = when (resolveNode) {
                is SimpleNode -> resolveNode.token.value
                is ObjectNode -> resolveNode.nodes
                is ArrayNode -> resolveNode.nodes
            }

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
                    MergingStrategy.SINGLE,
                    distinctValueCandidates.single()
                )
            }

            return@mapValues UnresolvedConflict(valueCandidates)
        }
    }

    fun forAllSubSchemas(schema: JsonSchema, callback: JsonSchema.() -> Unit) {
        val subSchemas = mutableListOf(schema)
        val subSchemaIt = subSchemas.iterator()

        while (subSchemaIt.hasNext()) {
            val subSchema = subSchemaIt.next()
            callback(subSchema)

            /*
            // Does this work properly with JSON Pointers?
            // There are plenty of similar scenarios (Fields of type JsonSchema in JsonSchema).
            subSchema.allOf?.let { subSchemas.addAll(it) }
            subSchema.anyOf?.let { subSchemas.addAll(it) }
            subSchema.oneOf?.let { subSchemas.addAll(it) }
            */

            subSchema.properties?.values?.let { subSchemas.addAll(it) }
        }
    }
}