package com.peachprivacy.tiltservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class TiltServiceApplication

fun main(args: Array<String>) {
    runApplication<TiltServiceApplication>(*args)
}
