package com.peachprivacy.tiltservice.project

import java.util.*

interface SchemaRepository {
    fun createValueDefinitions(schema: UUID, values: Map<String, Any?>): String?
    fun getValueDefinitions(schema: UUID, dependencies: Map<UUID, Any>): String?
    fun resolveObject(schema: UUID, dependencies: Map<UUID, Any>): String?
    fun get(id: UUID): String?
    fun set(id: UUID, schema: String)
    fun delete(id: UUID)
}