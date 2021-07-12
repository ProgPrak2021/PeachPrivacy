package com.peachprivacy.tiltservice.project

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpStatusCodeException
import java.util.*

@Service
class TemplateService @Autowired constructor(
    private val schemaRepository: SchemaRepository,
    private val templateRepository: TemplateRepository
) {
    fun getResolvedObject(id: UUID): String? {
        val dependencyMap = getDependencyMap(id)

        return schemaRepository.resolveObject(id, dependencyMap)
    }

    fun createValueDefinitions(id: UUID, values: Map<String, Any?>): String? {
        return schemaRepository.createValueDefinitions(id, values)
    }

    fun getValueDefinitionsOfTemplate(id: UUID): String? {
        val dependencyMap = getDependencyMap(id)

        return schemaRepository.getValueDefinitions(id, dependencyMap)
    }

    fun getDependencyMap(id: UUID): Map<UUID, Any> {
        val resolvedTemplates = mutableMapOf<List<UUID>, Template>()
        val resolvingTemplatesQueue = mutableListOf(listOf(id))

        // resolved templates not relevant. Relevant: Using the given templates
        // Purpose - Resolving through Schema repository for schemas in Templates
        // Consider doing this in the object mapper instead
        var currentQueueIndex = 0
        while (currentQueueIndex < resolvingTemplatesQueue.size) {
            val currentPath = resolvingTemplatesQueue[currentQueueIndex]
            val resolveId = currentPath.lastOrNull() ?: throw RuntimeException("Empty dependency path")
            val currentPrettyPath = currentPath.joinToString(", ") { "\"$it\"" }

            // Recursive dependencies could be problematic, but Hibernate maps the objects, not
            resolvedTemplates.toList().firstOrNull { (conflictCandidatePath, _) ->
                conflictCandidatePath.contains(resolveId)
            }?.let { (conflictPath, _) ->
                val conflictPrettyPath = conflictPath.joinToString(", ") { "\"$it\"" }

                throw HttpClientErrorException(HttpStatus.CONFLICT,
                    "{\"description\": \"Duplicate dependency detected (This should not actually be problematic, maybe remove this)\", " +
                            "\"path\": [$currentPrettyPath]}, " +
                            "\"conflictPath\": [$conflictPrettyPath]}")
            }

            val template = get(resolveId) ?: throw HttpClientErrorException(HttpStatus.NOT_FOUND,
                "{\"description\": \"A dependency couldn't be resolved\", \"path\": \"$currentPrettyPath\"}")

            resolvedTemplates[currentPath] = template

            template.parents.forEach { parentTemplate ->
                // ????????? Recursive dangers by Hibernate
                resolvingTemplatesQueue.add(currentPath + parentTemplate.id!!)
            }

            currentQueueIndex++
        }

        val dependencyMap = mutableMapOf<UUID, Any>()
        resolvedTemplates.keys.forEach { dependencyPath ->
            var accessMap = dependencyMap
            dependencyPath.forEach { subId ->
                accessMap = (accessMap.computeIfAbsent(subId) {
                    mutableMapOf<UUID, Any>()
                } as MutableMap<UUID, Any>)
            }
        }

        return dependencyMap
    }

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
    fun create(template: Template): Template {
        return templateRepository.save(template).also { savedTemplate ->
            schemaRepository.set(savedTemplate.id!!, savedTemplate.schema)
        }
    }
    fun delete(id: UUID) {
        return templateRepository.deleteById(id).also {
            schemaRepository.delete(id)
        }
    }
}