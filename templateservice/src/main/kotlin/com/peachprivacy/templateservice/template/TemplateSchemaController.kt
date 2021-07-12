package com.peachprivacy.templateservice.template

import com.peachprivacy.templateservice.TemplateSchema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/template/templates")
class TemplateSchemaController(
    @Autowired private val schemaService: TemplateSchemaService
) {
    @PostMapping("/data/{id}/values")
    fun addConstToTemplate(@PathVariable id: UUID, @RequestBody values: Map<String, Any?>): ResponseEntity<Any> {
        return schemaService.getSchema(id)?.let {
            val schema = schemaService.specifySchemaConst(it, values.mapKeys { (key, _) -> key.split("/") })

            schemaService.setSchema(schema)

            ResponseEntity.ok(schema)
        } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/data/{id}")
    fun justGetTemplate(@PathVariable id: UUID): ResponseEntity<String> {
        return schemaService.getSchema(id)?.let {
            ResponseEntity.ok(it.schema)
        } ?: ResponseEntity.notFound().build()
    }

    @PutMapping("/data/{id}")
    fun justSetTemplate(@PathVariable id: UUID, @RequestBody schema: String): ResponseEntity<String> {
        schemaService.setSchema(TemplateSchema(id, schema))

        // Guaranteed 200 at this point imo - Though, usually one would return the old saved schema at this ID I think (TODO?)
        return ResponseEntity.ok(schema)
    }

    @DeleteMapping("/data/{id}")
    fun justDeleteTemplate(@PathVariable id: UUID): ResponseEntity<String> {
        return schemaService.deleteSchema(id)?.let { deleted ->
            ResponseEntity.ok(deleted.schema)
        } ?: ResponseEntity.notFound().build()
    }

    /*
    // TODO Call ValidationController to get resolved object - non-matched variables are missing
    @GetMapping("/resolve/{id}")
    fun getResolvedTemplate(@PathVariable id: UUID): Template {
        return templateService.resolveTemplate(getRawTemplate(id))
    }
    */
}