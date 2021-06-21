package com.peachprivacy.tiltservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@EnableEurekaClient
@SpringBootApplication
class TiltServiceApplication {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.ant("/api/**"))
            .build()
            .securitySchemes(listOf(ApiKey("JWT", "Authorization", "header")))
            .securityContexts(
                listOf(
                    SecurityContext
                        .builder()
                        .operationSelector { "/api/account" in it.requestMappingPattern() }
                        .securityReferences(
                            listOf(
                                SecurityReference(
                                    "JWT",
                                    arrayOf(
                                        AuthorizationScope("global", "accessEverything")
                                    )
                                )
                            )
                        )
                        .build()
                )
            )
    }
}

fun main(args: Array<String>) {
    runApplication<TiltServiceApplication>(*args)
}
