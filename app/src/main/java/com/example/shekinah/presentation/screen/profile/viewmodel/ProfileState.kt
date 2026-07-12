package com.example.shekinah.presentation.screen.profile

sealed class ProfileImageState {
    object Idle : ProfileImageState()
    object Loading : ProfileImageState()
    data class Success(
        val name: String? = null,
        val email: String? = null,
        val photoUrl: String?
    ) : ProfileImageState()
    data class Error(val message: String) : ProfileImageState()
}

