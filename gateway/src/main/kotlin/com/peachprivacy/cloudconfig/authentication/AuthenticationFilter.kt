package com.peachprivacy.cloudconfig.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
@RefreshScope
class AuthenticationFilter @Autowired constructor(
    val authenticationService: AuthenticationService
) : GatewayFilter {

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val token = exchange.request.headers["Authorization"]?.firstOrNull() ?: run {
            return exchange.response.also { it.statusCode = HttpStatus.UNAUTHORIZED }
                .setComplete()
        }

        val claims = authenticationService.run {
            if (token.isExpired()) {
                return exchange.response.also { it.statusCode = HttpStatus.UNAUTHORIZED }
                    .setComplete()
            }

            token.claims()
        }

        // TODO: parse all needed fields
        exchange.request.mutate()
            .header("id", claims["id"]?.toString())
            .build()
        return chain.filter(exchange)
    }
}