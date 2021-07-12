package com.peachprivacy.tiltservice.project

import java.util.*

interface SchemaRepository {
    fun getValueDefinitions(schema: UUID, dependencies: Map<UUID, Any>): String?
    fun get(id: UUID): String?
    fun set(id: UUID, schema: String)
    fun delete(id: UUID)
}