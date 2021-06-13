package com.peachprivacy.tiltservice.project

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class TemplateService @Autowired constructor(
    private val schemaRepository: SchemaRepository,
    private val templateRepository: TemplateRepository
) {
    fun get(id: UUID): Template? {
        return templateRepository.findByIdOrNull(id)?.also { template ->
            schemaRepository.get(id)?.also { schema ->
                template.schema = schema
            }
        }
    }

    fun update(template: Template): Template {
        return templateRepository.save(template).also { savedTemplate ->
            schemaRepository.set(savedTemplate.id!!, savedTemplate.schema)
        }
    }

    fun create(template: Template): UUID {
        return templateRepository.save(template).also { savedTemplate ->
            schemaRepository.set(savedTemplate.id!!, savedTemplate.schema)
        }.id!!
    }

    fun delete(id: UUID) {
        return templateRepository.deleteById(id).also {
            schemaRepository.delete(id)
        }
    }
}