package com.peachprivacy.templateservice.resolve

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/template/resolve")
class ResolveController(
    @Autowired private val resolveService: ResolveService
) {
    @PostMapping("/values/{schema}")
    fun getConflicts(@PathVariable schema: UUID, @RequestBody dependencies: Map<UUID, Map<UUID, Any>>): ResponseEntity<List<ValueDefinitionItem<Any>>> {
        val valueDefinitions = resolveService.getAllValueDefinitions(schema, dependencies)

        return ResponseEntity.ok(valueDefinitions)
    }

    @PostMapping("/{schema}/object")
    fun getResolvedObject(@PathVariable schema: UUID, @RequestBody dependencies: Map<UUID, Map<UUID, Any>>): ResponseEntity<Map<String, Any?>> {
        val valueDefinitions = resolveService.getAllValueDefinitions(schema, dependencies)
        resolveService.getResolvedObject(valueDefinitions)?.let {
            return ResponseEntity.ok(it)
        } ?: return ResponseEntity.badRequest().build()
    }
}