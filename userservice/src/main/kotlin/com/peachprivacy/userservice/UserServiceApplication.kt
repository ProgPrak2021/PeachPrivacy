package com.peachprivacy.userservice

import com.peachprivacy.authentication.WebSecurityConfiguration
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans

@EnableEurekaClient
@SpringBootApplication
@ComponentScans(ComponentScan("com.peachprivacy.authentication"))
class UserServiceApplication

fun main(args: Array<String>) {
    val context = runApplication<UserServiceApplication>(*args)
    println(context.getBean<WebSecurityConfiguration>())
}
