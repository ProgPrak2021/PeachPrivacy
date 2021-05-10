package com.peachprivacy.tiltservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TiltServiceApplication

fun main(args: Array<String>) {
    runApplication<TiltServiceApplication>(*args)
}
