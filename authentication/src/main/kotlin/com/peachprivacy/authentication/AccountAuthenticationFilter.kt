package com.peachprivacy.authentication

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AccountAuthenticationFilter(private val authenticationManager: AuthenticationManager) :
    OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val id = request.getHeader("id")?.toIntOrNull()
        val email = request.getHeader("email")
        val authorities = request.getHeader("authorities")?.split(".")

        if (id != null && email != null && authorities != null) {
            val authentication = AccountAuthentication(id, email, authorities)
            SecurityContextHolder.getContext().authentication = authenticationManager.authenticate(authentication)
        }

        chain.doFilter(request, response)
    }
}