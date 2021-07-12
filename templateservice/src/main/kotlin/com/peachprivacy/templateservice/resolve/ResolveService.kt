package com.peachprivacy.templateservice.resolve

import com.peachprivacy.templateservice.template.TemplateSchemaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class ResolveService(
    @Autowired private val templateService: TemplateSchemaService,
    @Autowired private val resolveToolsService: ResolveToolsService
) {
    fun getResolvedObject(valueDefinitions: List<ValueDefinitionItem<Any>>): Map<String, Any?>? {
        val objectMap = mutableMapOf<String, Any?>()

        valueDefinitions.forEach { valueDefinitionItem ->
            val targetNode = valueDefinitionItem.path.dropLast(1).fold(objectMap) { pathNode, pathElement ->
                (pathNode[pathElement] as? MutableMap<String, Any?>) ?: mutableMapOf<String, Any?>().also { subNode ->
                    pathNode[pathElement] = subNode
                }
            }

            targetNode[valueDefinitionItem.path.last()] =
                (valueDefinitionItem.definition as? SuccessfulValueDefinition)?.value ?: return null
        }

        return objectMap
    }

    fun getAllValueDefinitions(schema: UUID, subSchemas: Map<UUID, Any>): List<ValueDefinitionItem<Any>> {
        val pathAttempts = subSchemas.map { (subSchema, value) ->
            ((value as? Map<String, Any>)?.let { subSubSchemas ->
                getAllValueDefinitions(subSchema, subSubSchemas.mapKeys { (key, _) -> key.toUUIDOrNull()!! })
            } ?: emptyList())
        }.map { valueDefinitionItem ->
            valueDefinitionItem.groupBy { it.path }.mapValues { (_, valueAttempts) ->
                if (valueAttempts.size != 1) throw RuntimeException("Non-unique paths")

                valueAttempts.first()
            }
        }.let { subSchemaDefinitions ->
            resolveToolsService.mergeValueDefinitions(schema, *subSchemaDefinitions.toTypedArray())
        }

        val values = resolveToolsService.getSchemaValues(
            templateService.getSchema(schema)?.schema ?: throw RuntimeException("No schema with ID $schema found")
        )

        val results = resolveToolsService.overrideValueDefinitions(schema, pathAttempts, values)

        return results.values.toList()
    }

    fun String.toUUIDOrNull(): UUID? = takeIf {
        this.matches("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}".toRegex())
    }?.let { UUID.fromString(it) }
}