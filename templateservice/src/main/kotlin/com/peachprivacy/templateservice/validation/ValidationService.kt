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