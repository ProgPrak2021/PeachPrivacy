package com.peachprivacy.templateservice.resolve

import com.worldturner.medeia.api.MedeiaApiBase
import com.worldturner.medeia.api.SchemaSource
import com.worldturner.medeia.api.StringSchemaSource
import com.worldturner.medeia.api.ValidationOptions
import com.worldturner.medeia.api.jackson.MedeiaJacksonApi
import com.worldturner.medeia.schema.model.JsonSchema
import com.worldturner.medeia.schema.model.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.net.URI

@Component
class JsonSchemaProvider(
    @Autowired private val jsonSchemaApi: MedeiaJacksonApi
) {
    // JsonSchema instances for some reason only available via Reflection -> TODO find intended way
    private val jsonSchemaGetMethod = MedeiaApiBase::class.java.getDeclaredMethod(
        "loadSchema",
        SchemaSource::class.java,
        ValidationOptions::class.java,
        MutableMap::class.java
    ).also {
        it.isAccessible = true
    }

    fun parseJsonSchema(schema: String): JsonSchema {
        val parsedSchema = jsonSchemaGetMethod.invoke(jsonSchemaApi,
            StringSchemaSource(schema),
            ValidationOptions.DEFAULT,
            mutableMapOf<URI, Any>()
        ) as Schema

        // TODO: Support JsonSchema with base URI
        if (parsedSchema !is JsonSchema)
        // With base URI could go right if parsed correctly - Requires context-dependent resolving instead of json pointers
            throw NotImplementedError("Given schemas NEED to be pure JsonSchemas in nature at the moment")

        return parsedSchema
    }
}