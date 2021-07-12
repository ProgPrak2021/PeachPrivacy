package com.peachprivacy.tiltservice.project

import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
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
    @GetMapping("/{id}/resolve")
    fun resolveById(@PathVariable id: UUID): ResponseEntity<String?> {
        return templateService.getValueDefinitionsOfTemplate(id)?.let { valueDefinitions ->
            ResponseEntity.ok(valueDefinitions)
        } ?: ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()  // TODO: Detailed error
    }

    @PostMapping("/{id}/resolve")
    fun specifyValues(@PathVariable id: UUID, @RequestBody values: Map<String, Any?>): ResponseEntity<String?> {
        return templateService.createValueDefinitions(id, values)?.let { newSchema ->
            ResponseEntity.ok(newSchema)
        } ?: ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()  // TODO: Detailed error
    }

    @ApiOperation("Access Template information using the uuid")
    @ApiImplicitParams(
        ApiImplicitParam(name = "id", value = "The uuid of the template to show information for"),
    )
    @ApiResponses(
        ApiResponse(
            code = 200,
            message = "The template object as JSON",
        ),
        ApiResponse(
            code = 404,
            message = "If no Template with this ID was found"
        )
    )
    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): ResponseEntity<Template> {
        return templateService.get(id)?.let { template ->
            ResponseEntity.ok(template)
        } ?: ResponseEntity.notFound().build()
    }
    @PostMapping
    fun create(@RequestBody template: Template, httpRequest: HttpServletRequest): ResponseEntity<Template> {
        if (template.id != null) return ResponseEntity.badRequest().build()
        if (template.children.isNotEmpty()) return ResponseEntity.badRequest().build()

        return templateService.create(template).let { createdTemplate ->
            val handlerUri = httpRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString()
            val uri = URI("$handlerUri/${createdTemplate.id}")
            ResponseEntity.created(uri).body(createdTemplate)
        }
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody template: Template): ResponseEntity<Template> {
        if (template.id != null && id != template.id) return ResponseEntity.badRequest().build()
        if (template.children.isNotEmpty()) return ResponseEntity.badRequest().build()

        return templateService.update(template).let {
            ResponseEntity.ok(it)
        }
    }
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Template> {
        return if (templateService.get(id) == null) {
            ResponseEntity.notFound().build()
        } else {
            templateService.delete(id)
            ResponseEntity.noContent().build()
        }
    }
}