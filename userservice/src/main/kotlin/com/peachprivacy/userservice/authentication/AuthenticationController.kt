package com.peachprivacy.userservice.authentication

import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/api/auth")
class AuthenticationController @Autowired constructor(val authenticationService: AuthenticationService) {
    @ApiOperation("Login using email, password and obtain an auth token.")
    @ApiImplicitParams(
        ApiImplicitParam(name = "email", value = "The email of the account to login with"),
        ApiImplicitParam(name = "password", value = "The password of the account to login with")
    )
    @ApiResponses(
        ApiResponse(
            code = 200,
            message = "Login successful, returining the auth token",
            response = String::class,
            examples = Example(
                ExampleProperty(
                    "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6IjciLCJhdXRob3JpdGllcyI6ImRlZmF1bHQsNyxhbnkiLCJzdWIiOiJwYXNrYWxwYWVzbGVyMjNAZ21haWwuY29tIiwiaWF0IjoxNjIzNjYwMTk3LCJleHAiOjE2MjQ4Njk3OTd9.gxWtcIcYgCDEfcDLb8G0mpnPwiw6ur9U42YtAKJaaE1wISCq2Oj1UTV6fmU6sXRayJRgky1tAlmvczR50rYBJw"
                )
            )
        ),
        ApiResponse(
            code = 400,
            message = "Login failed, credentials invalid or account does not exist"
        )
    )
    @PostMapping("/login")
    fun login(@Valid loginForm: LoginForm): ResponseEntity<String> {
        val token = authenticationService.login(loginForm.email, loginForm.password)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        return ResponseEntity.ok(token)
    }

    @ApiOperation("Validating a newly registered account, using the token of the confirmation mail",)
    @ApiImplicitParams(
        ApiImplicitParam(name = "token", value = "The token of the registration email"),
    )
    @ApiResponses(
        ApiResponse(
            code = 200,
            message = "Containing 'true' if the confirmation is valid, 'false' if not",
            response = Boolean::class,
            examples = Example(
                ExampleProperty("true"),
                ExampleProperty("false")
            )
        ),
    )
    @GetMapping("/verify/{token}")
    fun confirmEmail(@PathVariable token: String): Boolean {
        return authenticationService.confirmEmail(token)
    }

    @ApiOperation(
        value = "Register using email and password",
        notes = """
            Auth Token is not provided, login needed. 
            After successful request an confirmation email gets sent.
        """
    )
    @ApiImplicitParams(
        ApiImplicitParam(name = "email", value = "The email of the new account, that is not take by any other one"),
        ApiImplicitParam(name = "password", value = "The password of the new account")
    )
    @ApiResponses(
        ApiResponse(
            code = 200,
            message = "Registration successful",
        ),
        ApiResponse(
            code = 400,
            message = "Registration failed, invalid data or email already taken"
        )
    )
    @PostMapping("/register")
    fun register(@Valid registerForm: RegisterForm): ResponseEntity<Any> {
        val register = authenticationService.register(registerForm.email, registerForm.password)
        return ResponseEntity(if (register) HttpStatus.OK else HttpStatus.BAD_REQUEST)
    }

    @ApiOperation("Endpoint for requesting a password reset mail and token")
    @ApiImplicitParams(
        ApiImplicitParam(name = "email", value = "The email of the account to request the password reset for"),
    )
    @ApiResponses(
        ApiResponse(
            code = 200,
            message = "When email is in correct format, no matter if the request was successfully sent",
        ),
        ApiResponse(
            code = 400,
            message = "Only if email is provided in wrong format or not present"
        )
    )
    @PostMapping("/reset/request")
    fun passwordResetRequest(@Valid resetRequestForm: PasswordResetRequestForm) {
        authenticationService.resetRequest(resetRequestForm.email)
    }

    @ApiOperation("Endpoint for checking if a password reset token is valid")
    @ApiImplicitParams(
        ApiImplicitParam(name = "token", value = "The password reset token located inside the password reset mail"),
    )
    @ApiResponses(
        ApiResponse(
            code = 200,
            message = "Containing 'true' if the token is valid, 'false' if not",
            response = Boolean::class,
            examples = Example(
                ExampleProperty("true"),
                ExampleProperty("false")
            )
        ),
    )
    @GetMapping("/reset/{token}")
    fun passwordResetValid(@PathVariable token: String) = authenticationService.resetValid(token)

    @ApiOperation(
        value = "Reset the password of an account",
        notes = """
            Reset using the token of an password reset mail, emitted by a password reset request.
        """
    )
    @ApiImplicitParams(
        ApiImplicitParam(name = "token", value = "The valid password reset token"),
        ApiImplicitParam(name = "password", value = "The new password for the selected account")
    )
    @ApiResponses(
        ApiResponse(
            code = 200,
            message = "Password reset successful",
        ),
        ApiResponse(
            code = 400,
            message = "Invalid format of token / password or invalid token"
        )
    )
    @PostMapping("/reset")
    fun passwordReset(@Valid resetForm: PasswordResetForm): ResponseEntity<Any> {
        val reset = authenticationService.reset(resetForm.token, resetForm.password)
        return ResponseEntity(if (reset) HttpStatus.OK else HttpStatus.BAD_REQUEST)
    }
}