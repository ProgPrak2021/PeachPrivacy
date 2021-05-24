package com.peachprivacy.templateservice

import com.worldturner.medeia.api.jackson.MedeiaJacksonApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class TemplateServiceApplication {
    @Bean
    fun medeiaJacksonApi() = MedeiaJacksonApi()
}

fun main(args: Array<String>) {
    runApplication<TemplateServiceApplication>(*args)
}
