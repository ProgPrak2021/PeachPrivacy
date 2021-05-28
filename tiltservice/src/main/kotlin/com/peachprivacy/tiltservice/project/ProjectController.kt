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
    @GetMapping()
    fun get5RandomProjects(): ResponseEntity<List<Project>> {
        return ResponseEntity.ok(projectService.getAny(5))
    }

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

    // TODO Redundant ID in path, but proper REST would have it there instead of in RequestBody
    //  Path ID currently not used
    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody project: Project): ResponseEntity<Project> {
        return projectService.update(project).let {
            ResponseEntity.ok(it)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Project> {
        projectService.delete(id)
        return ResponseEntity.noContent().build()
    }
}