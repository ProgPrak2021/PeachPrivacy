package com.peachprivacy.userservice.authentication

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class LoginForm(
    @field:Email @field:NotBlank @field:Size(max = 255) var email: String,
    @field:NotBlank @field:Size(min = 6, max = 255) var password: String
)