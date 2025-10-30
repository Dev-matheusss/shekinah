package com.example.shekinah.presentation.screen.recoverPassword

data class RecoverPasswordState(
    val email: String = "",
    val success: Boolean = false,
    val message: String = ""
)