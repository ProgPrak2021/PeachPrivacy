package com.peachprivacy.cloudconfig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication
class CloudConfigApplication

fun main(args: Array<String>) {
    runApplication<CloudConfigApplication>(*args)
}
