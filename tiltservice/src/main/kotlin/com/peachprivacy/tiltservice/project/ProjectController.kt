package com.peachprivacy.tiltservice.project

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.HandlerMapping
import java.net.URI
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/tilt/projects")
class ProjectController @Autowired constructor(
    private val projectService: ProjectService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<Project> {
        return projectService.get(id)?.let { template ->
            ResponseEntity.ok(template)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping()
    fun create(@RequestBody project: Project, httpRequest: HttpServletRequest): ResponseEntity<Project> {
        return projectService.create(project).let { id ->
            val handlerUri = httpRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString()
            val uri = URI("$handlerUri/$id")
            ResponseEntity.created(uri).build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Project> {
        projectService.delete(id)
        return ResponseEntity.noContent().build()
    }
}