package com.peachprivacy.gateway.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component

@Component
class AuthenticationFilterFactory @Autowired constructor(private val authenticationFilter: AuthenticationFilter) :
    AbstractGatewayFilterFactory<Any>() {
    override fun apply(config: Any): GatewayFilter = authenticationFilter

    class Config
}