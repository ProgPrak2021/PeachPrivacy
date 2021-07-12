package com.peachprivacy.templateservice.resolve

import com.worldturner.medeia.schema.model.SimpleType

sealed class SchemaNodeInfo(
    open val depthTitles: List<String>,
    open val depthDescriptions: List<String>,
    open val type: SimpleType
)

data class ProvidedSchemaNodeValue(
    override val depthTitles: List<String>,
    override val depthDescriptions: List<String>,
    override val type: SimpleType,
    val value: Any?
) : SchemaNodeInfo(depthTitles, depthDescriptions, type)

data class MissingSchemaNodeValue(
    override val depthTitles: List<String>,
    override val depthDescriptions: List<String>,
    override val type: SimpleType
) : SchemaNodeInfo(depthTitles, depthDescriptions, type)
