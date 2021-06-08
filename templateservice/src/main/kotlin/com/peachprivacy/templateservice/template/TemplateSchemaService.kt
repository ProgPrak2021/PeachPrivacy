package com.peachprivacy.templateservice.template

import com.peachprivacy.templateservice.TemplateSchema
import org.springframework.stereotype.Service
import java.util.*

@Service
class TemplateSchemaService {
    /**
     * Fetches template from document db
     */
    fun getTemplate(id: UUID): TemplateSchema = TODO()

    /**
     * Resolves const values in schema, returning the resulting object (or an exception if required fields are missing)
     */
    fun resolveTemplate(schema: TemplateSchema): Map<String, Any?> = TODO()
}