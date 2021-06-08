package com.peachprivacy.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class WebSecurityConfiguration @Autowired constructor(val authenticationProvider: AccountAuthenticationProvider) :
    WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .sessionManagement().disable()
            .exceptionHandling().disable()
            .csrf().disable()
            //.addFilter(AccountAuthenticationFilter(authenticationManager()))
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authenticationProvider)
    }
}