package com.peachprivacy.userservice.authentication

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class PasswordResetRequestForm(
    @Email @NotBlank @Size(max = 255) var email: String
)