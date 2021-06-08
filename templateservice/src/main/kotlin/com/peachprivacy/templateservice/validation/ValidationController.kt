package com.peachprivacy.templateservice.validation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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
            val responseBody = pointerResolvers.mapKeys { entry -> entry.key.text }
            ResponseEntity.ok(responseBody)
        }
}