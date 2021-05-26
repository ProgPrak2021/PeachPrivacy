package com.peachprivacy.tiltservice.project

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.HandlerMapping
import java.net.URI
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/tilt/templates")
class TemplateController @Autowired constructor(
    private val templateService: TemplateService
) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): ResponseEntity<Template> {
        return templateService.get(id)?.let { template ->
            ResponseEntity.ok(template)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping()
    fun create(@RequestBody template: Template, httpRequest: HttpServletRequest): ResponseEntity<Template> {
        return templateService.create(template).let { id ->
            val handlerUri = httpRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString()
            val uri = URI("$handlerUri/$id")
            ResponseEntity.created(uri).build()
        }
    }

    // TODO Redundant ID in path, but proper REST would have it there instead of in RequestBody
    //  Path ID currently not used
    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody template: Template): ResponseEntity<Template> {
        return templateService.update(template).let {
            ResponseEntity.ok(it)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Template> {
        templateService.delete(id)
        return ResponseEntity.noContent().build()
    }
}