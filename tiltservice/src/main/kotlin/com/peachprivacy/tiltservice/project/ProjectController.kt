package com.peachprivacy.tiltservice.project

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
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
    @GetMapping()
    fun get5RandomProjects(): ResponseEntity<List<Project>> {
        return ResponseEntity.ok(projectService.getRandom(5))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<Project> {
        return projectService.get(id)?.let { template ->
            ResponseEntity.ok(template)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping()
    fun create(@RequestBody project: Project, httpRequest: HttpServletRequest): ResponseEntity<Project> {
        if (project.id != null) return ResponseEntity.badRequest().build()
        if (project.versions.size > 1) return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build()

        return projectService.create(project).let { id ->
            val handlerUri = httpRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString()
            val uri = URI("$handlerUri/$id")
            ResponseEntity.created(uri).build()
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody project: Project): ResponseEntity<Project> {
        if (project.id != null && id != project.id) return ResponseEntity.badRequest().build()

        return projectService.update(project.apply { this.id = id }).let {
            ResponseEntity.ok(it)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Project> {
        return if (projectService.get(id) == null) {
            ResponseEntity.notFound().build()
        } else {
            projectService.delete(id)
            ResponseEntity.noContent().build()
        }
    }
}