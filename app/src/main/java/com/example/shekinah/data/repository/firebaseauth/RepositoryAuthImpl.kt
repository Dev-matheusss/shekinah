package com.example.shekinah.data.repository.firebaseauth

import com.example.shekinah.data.api.fireauth.ApiService
import com.example.shekinah.data.model.AuthDto
import com.example.shekinah.data.model.RecoverDto
import com.google.firebase.auth.FirebaseAuth

class RepositoryAuthImpl(private val api: ApiService): RepositoryAuth {
    override suspend fun register( email: String, password: String): AuthDto {
        return api.register(email,password)
    }

    override suspend fun singIn(email: String, password: String): AuthDto {
        return api.singIn(email,password)
    }

    override suspend fun recoverPassword(email: String): RecoverDto{
        return api.recoverPassword(email)
    }
}