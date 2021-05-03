package com.peachprivacy.cloudconfig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CloudConfigApplication

fun main(args: Array<String>) {
    runApplication<CloudConfigApplication>(*args)
}
