package com.peachprivacy.authentication

import com.fasterxml.jackson.annotation.JsonIgnore
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

    @JsonIgnore
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        authorities.map { SimpleGrantedAuthority(it) }.toMutableList()

    @JsonIgnore
    override fun getCredentials(): Any? = null

    @JsonIgnore
    override fun getDetails(): Any = id

    @JsonIgnore
    override fun getPrincipal(): Any? = null

    @JsonIgnore
    override fun isAuthenticated(): Boolean = authenticated

    override fun setAuthenticated(isAuthenticated: Boolean) {
        authenticated = isAuthenticated
    }
}