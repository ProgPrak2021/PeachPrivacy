package com.peachprivacy.templateservice.template

import com.peachprivacy.templateservice.Template
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController("/api/template/templates")
class TemplateController(
    @Autowired private val templateService: TemplateService
) {
    @GetMapping("/raw/{id}")
    fun getRawTemplate(@PathVariable id: UUID): Template {
        return templateService.getTemplate(id)
    }

    /*
    // TODO Call ValidationController to get resolved object - non-matched variables are missing
    @GetMapping("/resolve/{id}")
    fun getResolvedTemplate(@PathVariable id: UUID): Template {
        return templateService.resolveTemplate(getRawTemplate(id))
    }
    */
}