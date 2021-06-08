package com.peachprivacy.authentication.authentication

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class LoginForm(
    @Email @NotBlank @Size(max = 255) var email: String,
    @NotBlank @Size(min = 6, max = 255) var password: String
)