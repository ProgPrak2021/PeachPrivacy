package com.peachprivacy.authentication.authentication

import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Int> {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): Account?

    fun findByEmailToken(token: String): Account?
}