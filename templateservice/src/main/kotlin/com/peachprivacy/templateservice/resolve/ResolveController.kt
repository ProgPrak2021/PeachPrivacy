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
    fun getConflicts(@RequestBody dependencies: Map<UUID, Map<UUID, Any>>, @PathVariable schema: UUID): ResponseEntity<List<ValueDefinitionItem<Any>>> {
        val valueDefinitions = resolveService.getAllValueDefinitions(schema, dependencies)

        return ResponseEntity.ok(valueDefinitions)
    }
}