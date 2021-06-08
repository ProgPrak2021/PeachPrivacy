package com.peachprivacy.authentication

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class AccountAuthenticationProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        authentication.isAuthenticated = true
        return authentication
    }

    override fun supports(authentication: Class<*>): Boolean = authentication == AccountAuthentication::class.java
}