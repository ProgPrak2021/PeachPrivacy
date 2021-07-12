package com.peachprivacy.templateservice.resolve

import com.worldturner.medeia.schema.model.SimpleType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class ResolveToolsService(
    @Autowired private val jsonSchemaProvider: JsonSchemaProvider
) {
    fun getSchemaValues(schema: String): Map<List<String>, SchemaNodeInfo> {
        val values = mutableMapOf<List<String>, SchemaNodeInfo>()
        val resolvingQueue = mutableListOf(listOf<String>() to jsonSchemaProvider.parseJsonSchema(schema))

        var queueIndex = 0
        while (queueIndex < resolvingQueue.size) {
            val (path, schemaElement) = resolvingQueue[queueIndex]

            val type = schemaElement.type?.let {
                if (it.size == 1) it.first()
                else SimpleType.STRING
            } ?: SimpleType.STRING

            val value = schemaElement.const?.toString()?.let { definedConst ->
                when {
                    type == SimpleType.NULL || definedConst == "null" -> null
                    // TODO other reserved keywords? (true, false, numbers...)
                    type == SimpleType.STRING -> definedConst.removeSurrounding("\"")
                    else -> definedConst
                }
            }

            (values[path.dropLast(1)] ?: MissingSchemaNodeValue(
                listOf(), listOf(), SimpleType.STRING   // Pseudo
            )).let { upperSchemaNodeInfo ->
                if (schemaElement.const == null) {
                    if (type != SimpleType.OBJECT) values[path] = MissingSchemaNodeValue(
                        upperSchemaNodeInfo.depthTitles +
                                (schemaElement.title ?: schemaElement.jsonPointer.toString()),
                        upperSchemaNodeInfo.depthDescriptions +
                                (schemaElement.description ?: schemaElement.jsonPointer.toString()),
                        type
                    )
                } else {
                    values[path] = ProvidedSchemaNodeValue(
                        upperSchemaNodeInfo.depthTitles +
                                (schemaElement.title ?: schemaElement.jsonPointer.toString()),
                        upperSchemaNodeInfo.depthDescriptions +
                                (schemaElement.description ?: schemaElement.jsonPointer.toString()),
                        type,
                        value
                    )
                }
            }

            schemaElement.properties?.forEach { (name, value) ->
                resolvingQueue.add(path + name to value)
            }

            queueIndex++
        }

        return values
    }

    fun overrideValueDefinitions(
        schema: UUID,
        parentValueDefinitions: Map<List<String>, ValueDefinitionItem<Any>>,
        childValues: Map<List<String>, SchemaNodeInfo>
    ): Map<List<String>, ValueDefinitionItem<Any>> {
        // Pass inherited values as fillers
        val valueDefinitions: MutableMap<List<String>, ValueDefinitionItem<out Any>> = parentValueDefinitions.toMutableMap()

        childValues.forEach { (path, childValue) ->
            val parentValueDefinition = valueDefinitions[path]

            valueDefinitions[path] =
                if (parentValueDefinition == null) childValue.let { valueHolder ->
                    val valueDefinition = (valueHolder as? ProvidedSchemaNodeValue)?.value?.let { value ->
                        (value as? Collection<*>)?.let { valueAsCollection ->
                            SimpleValueDefinition(schema, MergingStrategy.ALL, valueAsCollection)
                        } ?: SimpleValueDefinition(schema, MergingStrategy.SINGLE, value)
                    }

                    ValueDefinitionItem(
                        path,
                        valueHolder.depthTitles.joinToString(" - "),
                        valueHolder.depthDescriptions.joinToString(" - "),
                        valueHolder.type.name.toLowerCase(),
                        valueDefinition
                    )
                }
                else childValue.let { valueHolder ->
                    val valueDefinition = (valueHolder as? ProvidedSchemaNodeValue)?.value?.let { value ->
                        (value as? Collection<Any>)?.let { valueAsCollection ->
                            OverridingValueDefinition(
                                schema,
                                MergingStrategy.ALL,
                                parentValueDefinition.definition!! as ValueAttempt<Collection<Any>>,
                                valueAsCollection
                            )
                        } ?: OverridingValueDefinition(
                            schema,
                            MergingStrategy.SINGLE,
                            parentValueDefinition.definition!! as ValueAttempt<Any>,
                            value
                        )
                    }

                    ValueDefinitionItem(
                        path,
                        valueHolder.depthTitles.joinToString(" - "),
                        valueHolder.depthDescriptions.joinToString(" - "),
                        valueHolder.type.name.toLowerCase(),
                        valueDefinition
                    )
                }
        }

        return valueDefinitions.mapValues { (_, valueDefinition) -> valueDefinition as ValueDefinitionItem<Any> }
    }

    fun mergeValueDefinitions(
        schema: UUID,
        vararg inheritedValueDefinitions: Map<List<String>, ValueDefinitionItem<Any>>
    ): Map<List<String>, ValueDefinitionItem<Any>> {
        val pathCandidates = mutableMapOf<List<String>, MutableList<ValueDefinitionItem<Any>>>()
        inheritedValueDefinitions.forEach { conflicts ->
            conflicts.forEach { (path, valueDefinition) ->
                pathCandidates.computeIfAbsent(path) {
                    mutableListOf()
                }.add(valueDefinition)
            }
        }

        return pathCandidates.mapValues { (_, allValueAttempts) ->
            /*
            if (allValueAttempts.isEmpty())
                return@mapValues FailedMergeValueDefinition(schema, allValueAttempts, MergingStrategy.NONE)
            */
            if (allValueAttempts.size == 1)
                return@mapValues allValueAttempts.first()

            val relevantAttempts = allValueAttempts.toMutableList()

            if (relevantAttempts.any { it.definition?.let { attempt ->
                    attempt is FailedMergeValueDefinition && attempt.candidates.isNotEmpty() } == true } ||
                relevantAttempts.all { it.definition == null || it.definition is FailedMergeValueDefinition })
                return@mapValues allValueAttempts.first().copy(
                    definition = FailedMergeValueDefinition(schema, allValueAttempts, MergingStrategy.NONE)
                )

            relevantAttempts.removeIf { it.definition?.let {
                it is FailedMergeValueDefinition
            } != false }

            val valueAllMergedDefinition = relevantAttempts.run {
                var allValues: MutableList<Any?>? = null
                val removedAttempts = mutableListOf<ValueDefinitionItem<Any>>()

                removeIf { valueDefinitionItem ->
                    valueDefinitionItem.definition?.let { valueAttempt ->
                        if (valueAttempt !is SuccessfulValueDefinition) return@removeIf false
                        if (valueAttempt.preferredMergingStrategy != MergingStrategy.ALL) return@removeIf false

                        allValues = (allValues ?: mutableListOf()).apply {
                            addAll(valueAttempt.value.let { (it as? Collection<*>) ?: listOf(it) })
                        }
                    }

                    removedAttempts.add(valueDefinitionItem)

                    return@removeIf true
                }

                return@run removedAttempts.takeIf { it.isNotEmpty() }?.let {
                    it.first().copy(
                        definition = MergedValueDefinition(
                            schema,
                            it,
                            MergingStrategy.ALL,
                            MergingStrategy.ALL,
                            allValues
                        )
                    )
                }
            }

            valueAllMergedDefinition?.let { allValueAttempts.add(it) }

            return@mapValues relevantAttempts.filterIsInstance<SuccessfulValueDefinition<Any>>().toMutableList()
                .let { relevantSuccessValueDefs ->
                    if (relevantSuccessValueDefs.isEmpty() && valueAllMergedDefinition != null)
                        return@mapValues valueAllMergedDefinition

                    (valueAllMergedDefinition?.definition as? SuccessfulValueDefinition<Any>)?.let {
                        relevantSuccessValueDefs.add(it)
                    }

                    // At this point, at least one conflict is a SINGLE conflict
                    val distinctValueDefinitions = relevantSuccessValueDefs.distinctBy { it.value }
                    if (distinctValueDefinitions.size == 1) {
                        return@let MergedValueDefinition(
                            schema,
                            allValueAttempts,
                            MergingStrategy.SINGLE,
                            MergingStrategy.SINGLE,
                            distinctValueDefinitions.first().value
                        )
                    } else {
                        return@let FailedMergeValueDefinition(
                            schema,
                            allValueAttempts,
                            MergingStrategy.SINGLE
                        )
                    }
                }.let {
                    allValueAttempts.first().copy(
                        definition = it
                    )
                }
        }

        /*

        val pathCandidates = mutableMapOf<List<String>, MutableList<ValueDefinitionItem<Any>>>()
        inheritedValueDefinitions.forEach { conflicts ->
            conflicts.forEach { (path, valueDefinition) ->
                pathCandidates.computeIfAbsent(path) {
                    mutableListOf()
                }.add(valueDefinition)
            }
        }

        return pathCandidates.mapValues { (_, allValueAttempts) ->
            /*
            if (allValueAttempts.isEmpty())
                return@mapValues FailedMergeValueDefinition(schema, allValueAttempts, MergingStrategy.NONE)
            */
            if (allValueAttempts.size == 1)
                return@mapValues allValueAttempts.first()

            val relevantAttempts = allValueAttempts.toMutableList()

            if (relevantAttempts.any { it.definition?.let { attempt ->
                    attempt is FailedMergeValueDefinition && attempt.candidates.isNotEmpty() } == true } ||
                relevantAttempts.all { it.definition == null || it.definition is FailedMergeValueDefinition })
                return@mapValues FailedMergeValueDefinition(schema, allValueAttempts, MergingStrategy.NONE)

            relevantAttempts.removeIf { it is FailedMergeValueDefinition }

            val valueAllMergedDefinition = relevantAttempts.run {
                var allValues: MutableList<Any?>? = null
                val removedAttempts = mutableListOf<ValueAttempt<Any>>()

                removeIf { valueAttempt ->
                    if (valueAttempt !is SuccessfulValueDefinition) return@removeIf false
                    if (valueAttempt.preferredMergingStrategy != MergingStrategy.ALL) return@removeIf false

                    allValues = (allValues ?: mutableListOf()).apply {
                        addAll(valueAttempt.value.let { (it as? Collection<*>) ?: listOf(it) })
                    }

                    removedAttempts.add(valueAttempt)

                    return@removeIf true
                }

                return@run removedAttempts.takeIf { it.isNotEmpty() }?.let {
                    MergedValueDefinition(
                        schema,
                        it,
                        MergingStrategy.ALL,
                        MergingStrategy.ALL,
                        allValues
                    )
                }
            }

            valueAllMergedDefinition?.let { allValueAttempts.add(it) }

            return@mapValues relevantAttempts.filterIsInstance<SuccessfulValueDefinition<Any>>().toMutableList()
                .let { relevantSuccessValueDefs ->
                    if (relevantSuccessValueDefs.isEmpty() && valueAllMergedDefinition != null)
                        return@mapValues valueAllMergedDefinition

                    valueAllMergedDefinition?.let { relevantSuccessValueDefs.add(it) }

                    // At this point, at least one conflict is a SINGLE conflict
                    val distinctValueDefinitions = relevantSuccessValueDefs.distinctBy { it.value }
                    if (distinctValueDefinitions.size == 1) {
                        return@let MergedValueDefinition(
                            schema,
                            allValueAttempts,
                            MergingStrategy.SINGLE,
                            MergingStrategy.SINGLE,
                            distinctValueDefinitions.first().value
                        )
                    } else {
                        return@let FailedMergeValueDefinition(
                            schema,
                            allValueAttempts,
                            MergingStrategy.SINGLE
                        )
                    }
                }
        }

         */
    }
}