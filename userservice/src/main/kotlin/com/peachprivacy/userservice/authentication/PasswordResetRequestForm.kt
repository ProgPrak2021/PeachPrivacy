package com.peachprivacy.userservice.authentication

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class PasswordResetRequestForm(
    @field:Email @field:NotBlank @field:Size(max = 255) var email: String
)