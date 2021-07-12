package com.peachprivacy.templateservice.template

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.*
import com.peachprivacy.templateservice.TemplateSchema
import com.peachprivacy.templateservice.resolve.JsonSchemaProvider
import com.worldturner.medeia.parser.JsonTokenData
import com.worldturner.medeia.parser.SimpleNode
import com.worldturner.medeia.pointer.JsonPointer
import com.worldturner.medeia.schema.model.JsonSchema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TemplateSchemaService(
    @Autowired private val jsonSchemaProvider: JsonSchemaProvider,
    @Autowired private val schemaRepository: TemplateSchemaRepository
){
    /**
     * Fetches schema from document db
     */
    fun getSchema(id: UUID): TemplateSchema? = schemaRepository.findById(id).orElseGet { null }

    /**
     * Updates schema in document db
     */
    fun setSchema(schema: TemplateSchema): TemplateSchema = schemaRepository.save(schema)

    /**
     * Deletes schema from document db
     */
    fun deleteSchema(id: UUID): TemplateSchema? = schemaRepository.findById(id).orElseGet { null }?.also {
        schemaRepository.deleteById(id)
    }

    fun specifySchemaConst(schema: TemplateSchema, values: Map<List<String>, Any?>): TemplateSchema {
        val objectMapper = ObjectMapper()
        val rootNode = objectMapper.readTree(schema.schema) as ObjectNode

        values.forEach { (purePath, value) ->
            val realPath = purePath.let {
                mutableListOf<String>().also { createdPath ->
                    purePath.forEach {
                        createdPath.add("properties")
                        createdPath.add(it)
                    }
                    createdPath.add("const")
                }
            }

            val targetNode = realPath.dropLast(1).fold(rootNode) { node, pathElement ->
                (node[pathElement] as? ObjectNode) ?: ObjectNode(
                    objectMapper.nodeFactory
                ).let { node.set(pathElement, it) }
            }

            targetNode.set<ValueNode>(realPath.last(), when(value) {
                is Int -> IntNode(value)
                is Long -> LongNode(value)
                is Float -> FloatNode(value)
                is Double -> DoubleNode(value)
                is Boolean -> BooleanNode.valueOf(value)
                is Short -> ShortNode(value)
                else -> TextNode(value?.toString() ?: "null")
            })
        }

        return TemplateSchema(schema.id, rootNode.toString())
    }
}