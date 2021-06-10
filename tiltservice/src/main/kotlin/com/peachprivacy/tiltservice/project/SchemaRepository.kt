package com.peachprivacy.tiltservice.project

import java.util.*

interface SchemaRepository {
    fun get(id: UUID): String?
    fun set(id: UUID, schema: String)
    fun delete(id: UUID)
}