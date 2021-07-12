package com.peachprivacy.gateway.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
@RefreshScope
class AuthenticationFilter @Autowired constructor(
    val authenticationService: AuthenticationService
) : GlobalFilter {
    private val disallowedHeaders = setOf("id", "email", "authorities")

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        if (disallowedHeaders.any { it in exchange.request.headers }) {
            return exchange.response.also { it.statusCode = HttpStatus.BAD_REQUEST }
                .setComplete()
        }

        val token = exchange.request.headers["Authorization"]?.firstOrNull()?.replace("Bearer ", "") ?: run {
            return chain.filter(exchange)
        }

        val claims = authenticationService.run {
            if (token.isExpired()) {
                return exchange.response.also { it.statusCode = HttpStatus.UNAUTHORIZED }
                    .setComplete()
            }

            token.claims()
        }

        exchange.request.mutate()
            .header("id", claims["id"]?.toString())
            .header("email", claims.subject)
            .header("authorities", claims["authorities"]?.toString())
            .build()
        return chain.filter(exchange)
    }
}