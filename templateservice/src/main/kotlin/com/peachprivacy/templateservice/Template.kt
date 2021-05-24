package com.peachprivacy.templateservice

import com.worldturner.medeia.api.JsonSchemaVersion
import com.worldturner.medeia.api.StringSchemaSource
import com.worldturner.medeia.api.jackson.MedeiaJacksonApi

data class Template(
    private val jsonSchemaApi: MedeiaJacksonApi,
    val schema: String
) {
    val validator = jsonSchemaApi.loadSchema(StringSchemaSource(schema, JsonSchemaVersion.DRAFT07))

}