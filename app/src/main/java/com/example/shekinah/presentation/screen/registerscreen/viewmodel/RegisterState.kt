package com.example.shekinah.presentation.screen.registerscreen.viewmodel

import com.example.shekinah.domain.model.Auth

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val result: Auth? = null
)
