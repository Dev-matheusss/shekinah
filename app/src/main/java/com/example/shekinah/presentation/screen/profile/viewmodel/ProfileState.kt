package com.example.shekinah.presentation.screen.profile.viewmodel

sealed class ProfileState {
    object Idle : ProfileState()
    object Loading : ProfileState()
    data class Success(
        val name: String? = null,
        val photoUrl: String?
    ) : ProfileState()
    data class Error(val message: String) : ProfileState()
}

