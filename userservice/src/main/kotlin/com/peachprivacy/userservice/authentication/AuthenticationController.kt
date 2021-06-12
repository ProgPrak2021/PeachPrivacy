package com.peachprivacy.userservice.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/api/auth")
class AuthenticationController @Autowired constructor(val authenticationService: AuthenticationService) {
    @PostMapping("/login")
    fun login(@Valid loginForm: LoginForm): ResponseEntity<Any> {
        val token = authenticationService.login(loginForm.email, loginForm.password)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        return ResponseEntity.ok(token)
    }

    @GetMapping("/verify/{token}")
    fun confirmEmail(@PathVariable token: String): Boolean {
        return authenticationService.confirmEmail(token)
    }

    @PostMapping("/register")
    fun register(@Valid registerForm: RegisterForm): ResponseEntity<Any> {
        val register = authenticationService.register(registerForm.email, registerForm.password)
        return ResponseEntity(if (register) HttpStatus.OK else HttpStatus.BAD_REQUEST)
    }
}