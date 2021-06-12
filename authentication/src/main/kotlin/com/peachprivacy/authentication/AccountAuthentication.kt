package com.peachprivacy.authentication

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class AccountAuthentication(
    val id: Int,
    val email: String,
    private val authorities: Iterable<String>,
    private var authenticated: Boolean = false
) : Authentication {
    override fun getName(): String = email

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        authorities.map { SimpleGrantedAuthority(it) }.toMutableList()

    override fun getCredentials(): Any? = null

    override fun getDetails(): Any = id

    override fun getPrincipal(): Any? = null

    override fun isAuthenticated(): Boolean = authenticated

    override fun setAuthenticated(isAuthenticated: Boolean) {
        authenticated = isAuthenticated
    }
}