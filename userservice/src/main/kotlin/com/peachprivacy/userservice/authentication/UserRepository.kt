package com.peachprivacy.userservice.authentication

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Account, Int> {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): Account?
}