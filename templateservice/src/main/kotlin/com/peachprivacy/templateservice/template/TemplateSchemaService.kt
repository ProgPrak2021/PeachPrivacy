package com.peachprivacy.templateservice.template

import com.peachprivacy.templateservice.TemplateSchema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TemplateSchemaService(
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

    /**
     * Resolves const values in schema, returning the resulting object (or an exception if required fields are missing)
     */
    fun resolveSchema(schema: TemplateSchema): Map<String, Any?> = TODO()
}