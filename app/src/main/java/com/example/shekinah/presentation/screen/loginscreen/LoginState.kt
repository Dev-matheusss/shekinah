package com.example.shekinah.presentation.screen.loginscreen

import com.example.shekinah.domain.model.Auth

data class LoginState(
    val email: String,
    val password: String,
    val result: Auth? = null
)
