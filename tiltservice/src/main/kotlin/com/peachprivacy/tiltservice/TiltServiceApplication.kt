package com.peachprivacy.tiltservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans

@EnableEurekaClient
@SpringBootApplication
@ComponentScans(ComponentScan("com.peachprivacy.authentication"))
class TiltServiceApplication

fun main(args: Array<String>) {
    runApplication<TiltServiceApplication>(*args)
}
