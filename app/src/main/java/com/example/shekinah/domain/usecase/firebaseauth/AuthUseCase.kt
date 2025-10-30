package com.example.shekinah.domain.usecase.firebaseauth

import com.example.shekinah.data.model.RecoverDto
import com.example.shekinah.domain.model.Auth

interface AuthUseCase {
    suspend fun register(email: String, password: String): Auth

    suspend fun singIn(email: String, password: String): Auth

    suspend fun recoverPassword(email: String): RecoverDto
}