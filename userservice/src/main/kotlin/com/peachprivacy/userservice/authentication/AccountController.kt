package com.peachprivacy.userservice.authentication

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController {
    @GetMapping("/valid")
    @PreAuthorize("isAuthenticated() && hasAuthority('sdf')")
    fun valid() {
    }
}