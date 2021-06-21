package com.peachprivacy.userservice.authentication

import com.peachprivacy.authentication.AccountAuthentication
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import io.swagger.annotations.Authorization
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController {

    @ApiOperation(
        value = "Get the account information of the current account",
        authorizations = [Authorization("Authorization")]
    )
    @ApiResponses(
        ApiResponse(
            code = 200,
            message = "Authenticated, returning the account object",
        ),
        ApiResponse(
            code = 401,
            message = "Not authenticated"
        )
    )
    @GetMapping("/info")
    @PreAuthorize("isAuthenticated()")
    fun valid() = SecurityContextHolder.getContext().authentication as AccountAuthentication
}