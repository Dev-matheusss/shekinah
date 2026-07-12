package com.example.shekinah.presentation.screen.recoverpassword

data class RecoverPasswordState(
    val email: String = "",
    val success: Boolean = false,
    val message: String = ""
)