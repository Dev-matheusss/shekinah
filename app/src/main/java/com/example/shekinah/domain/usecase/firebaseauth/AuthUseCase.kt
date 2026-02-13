package com.example.shekinah.domain.usecase.firebaseauth

import android.net.Uri
import com.example.shekinah.data.model.RecoverDto
import com.example.shekinah.domain.model.Auth

interface AuthUseCase {
    suspend fun register(email: String, password: String, name: String, imageUri: Uri): Auth
    fun getCurrentUserName(): String?

    suspend fun singIn(email: String, password: String): Auth

    suspend fun recoverPassword(email: String): RecoverDto

    suspend fun logout()
}