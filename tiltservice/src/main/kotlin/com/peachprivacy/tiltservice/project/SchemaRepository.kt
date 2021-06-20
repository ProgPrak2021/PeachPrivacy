package com.peachprivacy.tiltservice.project

import java.util.*

interface SchemaRepository {
    fun getMergedPaths(ids: Collection<UUID>): String?
    fun getMerged(ids: Collection<UUID>): String?
    fun get(id: UUID): String?
    fun set(id: UUID, schema: String)
    fun delete(id: UUID)
}