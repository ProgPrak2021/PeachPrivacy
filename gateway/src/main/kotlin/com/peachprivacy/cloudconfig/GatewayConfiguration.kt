package com.peachprivacy.cloudconfig

import com.peachprivacy.cloudconfig.authentication.AuthenticationFilter
import org.springframework.cloud.gateway.route.builder.BooleanSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.UriSpec
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayConfiguration(val authenticationFilter: AuthenticationFilter) {

    @Bean
    fun routes(routeLocatorBuilder: RouteLocatorBuilder) = routeLocatorBuilder.routes {
        route("userservice") {
            path("/api/auth/**")
                .uri("lb://userservice")
            path("/api/user/**")
                .authorized()
                .uri("lb://userservice")
        }
    }

    fun BooleanSpec.authorized(): UriSpec = filters { it.filter(authenticationFilter) }
}