package com.peachprivacy.tiltservice.template

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.HandlerMapping
import java.net.URI
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/tilt")
class TemplateController @Autowired constructor(
    private val templateService: TemplateService
) {
    @GetMapping("/templates/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<Template> {
        return templateService.get(id)?.let { template ->
            ResponseEntity.ok(template)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping("/templates")
    fun create(@RequestBody template: Template, httpRequest: HttpServletRequest): ResponseEntity<Template> {
        return templateService.create(template).let { id ->
            val handlerUri = httpRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString()
            val uri = URI("$handlerUri/$id")
            ResponseEntity.created(uri).build()
        }
    }

    @DeleteMapping("/templates/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Template> {
        templateService.delete(id)
        return ResponseEntity.noContent().build()
    }
}