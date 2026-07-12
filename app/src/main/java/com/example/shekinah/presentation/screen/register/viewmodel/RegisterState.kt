package com.example.shekinah.presentation.screen.register.viewmodel

import android.net.Uri
import com.example.shekinah.domain.model.Auth
import com.example.shekinah.presentation.screen.profile.viewmodel.ProfileState

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val imageState: ProfileState = ProfileState.Idle,
    val imageUri: Uri? = null,
    val result: Auth? = null
)
