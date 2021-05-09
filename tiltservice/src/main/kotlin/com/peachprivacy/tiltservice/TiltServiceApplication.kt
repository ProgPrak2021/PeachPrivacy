package com.peachprivacy.tiltservice

import com.peachprivacy.tiltservice.template.Template
import com.peachprivacy.tiltservice.template.TemplateRepository
import com.peachprivacy.tiltservice.template.VersionedTemplate
import com.peachprivacy.tiltservice.template.VersionedTemplateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.annotation.PostConstruct

@SpringBootApplication
class TiltServiceApplication {
    @Autowired
    lateinit var templateRepository: TemplateRepository

    @Autowired
    lateinit var versionedTemplateRepository: VersionedTemplateRepository

    @PostConstruct
    fun test() {
        println("ITS TIME!!!!!!!!!!!!!!!!!!!!")
        val template123 = templateRepository.save(Template().apply {
            name = "Test Template"
            authority = "public"
        })

        val vt1 = VersionedTemplate().apply {
            version = 42
            template = template123
        }
        val vt2 = VersionedTemplate().apply {
            version = 42
            template = template123
            parents = mutableListOf(
                vt1
            )
        }
        versionedTemplateRepository.save(vt1)
        versionedTemplateRepository.save(vt2)
    }
}

fun main(args: Array<String>) {
    runApplication<TiltServiceApplication>(*args)
}
