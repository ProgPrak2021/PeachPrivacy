package com.peachprivacy.userservice.authentication

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class PasswordResetForm(
    @field:NotBlank var token: String,
    @field:NotBlank @field:Size(min = 6, max = 255) var password: String
)