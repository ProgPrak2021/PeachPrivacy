package com.peachprivacy.templateservice.template

import com.peachprivacy.templateservice.Template
import org.springframework.stereotype.Service
import java.util.*

@Service
class TemplateService {
    /**
     * Fetches template from document db
     */
    fun getTemplate(id: UUID): Template = TODO()

    /**
     * Resolves const values in schema, returning the resulting object (or an exception if required fields are missing)
     */
    fun resolveTemplate(template: Template): Map<String, Any?> = TODO()
}