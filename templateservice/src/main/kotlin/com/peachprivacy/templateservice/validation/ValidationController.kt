package com.peachprivacy.templateservice.validation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/api/template/validation")
class ValidationController(
    @Autowired private val validationService: ValidationService
) {
    @GetMapping("/resolve/object")
    fun getResolvedObject(@RequestBody template: String): ResponseEntity<Map<String, Any?>> =
        validationService.getResolvedObject(template)?.let { resolvedObject ->
            ResponseEntity.ok(resolvedObject)
        } ?: ResponseEntity.status(HttpStatus.CONFLICT).build()

    @GetMapping("/resolve")
    fun getResolvedPaths(@RequestBody template: String): ResponseEntity<Map<String, Any?>> =
        validationService.getResolvedJsonPointers(template).let { pointerResolvers ->
            ResponseEntity.ok(pointerResolvers)
        }

    @PostMapping("/resolve/merge/path")
    fun resolvePaths(@RequestBody schemas: Array<String>): ResponseEntity<Map<String, Any?>> {
        val resolvedSchemas = schemas.map { unparsedSchema ->
            validationService.getResolvedJsonPointers(unparsedSchema)
        }

        return validationService.mergeResolvedJsonPointers(resolvedSchemas).let { resolvedObject ->
            ResponseEntity.ok(resolvedObject)
        }
    }

    @PostMapping("/resolve/merge")
    fun resolveComplete(@RequestBody schemas: Array<String>): ResponseEntity<Map<String, Any?>> {
        val resolvedSchemas = schemas.map { unparsedSchema ->
            validationService.getResolvedJsonPointers(unparsedSchema)
        }

        return validationService.mergeResolvedJsonPointers(resolvedSchemas).let { conflictPointers ->
            validationService.getResolvedObject(conflictPointers)
        }?.let { resolvedObject ->
            ResponseEntity.ok(resolvedObject)
        } ?: ResponseEntity.status(HttpStatus.CONFLICT).build()
    }
}