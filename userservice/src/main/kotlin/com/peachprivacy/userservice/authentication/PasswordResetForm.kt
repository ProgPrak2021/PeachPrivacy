package com.peachprivacy.userservice.authentication

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class PasswordResetForm(
    @NotBlank var token: String,
    @NotBlank @Size(min = 6, max = 255) var password: String
)