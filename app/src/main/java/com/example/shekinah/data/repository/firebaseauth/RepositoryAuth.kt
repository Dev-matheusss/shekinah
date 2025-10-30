package com.example.shekinah.data.repository.firebaseauth

import com.example.shekinah.data.model.AuthDto
import com.example.shekinah.data.model.RecoverDto

interface RepositoryAuth {
    suspend fun register( email: String, password: String): AuthDto

    suspend fun singIn(email: String, password: String): AuthDto

    suspend fun recoverPassword(email: String): RecoverDto
}