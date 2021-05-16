package com.peachprivacy.cloudconfig.authentication

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import javax.annotation.PostConstruct

@Service
class AuthenticationService {
    @Value("\${jwt.secret}")
    lateinit var secret: String
    lateinit var key: Key

    @PostConstruct
    fun initializeKey() {
        key = Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun String.claims(): Claims = Jwts
        .parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(this)
        .body

    fun String.isExpired(): Boolean = claims().expiration.before(Date())
}