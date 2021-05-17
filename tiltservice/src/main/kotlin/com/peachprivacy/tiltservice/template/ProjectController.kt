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
class ProjectController @Autowired constructor(
    private val projectService: ProjectService
) {
    @GetMapping("/projects/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<Project> {
        return projectService.get(id)?.let { template ->
            ResponseEntity.ok(template)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping("/projects")
    fun create(@RequestBody project: Project, httpRequest: HttpServletRequest): ResponseEntity<Project> {
        return projectService.create(project).let { id ->
            val handlerUri = httpRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString()
            val uri = URI("$handlerUri/$id")
            ResponseEntity.created(uri).build()
        }
    }

    @DeleteMapping("/projects/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Project> {
        projectService.delete(id)
        return ResponseEntity.noContent().build()
    }
}