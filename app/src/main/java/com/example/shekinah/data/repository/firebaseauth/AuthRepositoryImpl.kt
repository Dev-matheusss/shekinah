package com.example.shekinah.data.repository.firebaseauth

import android.net.Uri
import com.example.shekinah.data.datasource.fireauth.AuthDataSource
import com.example.shekinah.data.model.AuthDto
import com.example.shekinah.data.model.RecoverDto
import com.google.firebase.auth.FirebaseAuth

class AuthRepositoryImpl(private val api: AuthDataSource) : AuthRepository {
    override suspend fun register(email: String, password: String, name: String, imageUri: Uri): AuthDto {
        return api.register(email, password, name, imageUri)
    }

    override fun getCurrentUserName(): String? {
        return FirebaseAuth.getInstance().currentUser?.displayName
    }

    override suspend fun singIn(email: String, password: String): AuthDto {
        return api.singIn(email, password)
    }

    override suspend fun recoverPassword(email: String): RecoverDto {
        return api.recoverPassword(email)
    }

    override suspend fun logout() {
        api.logout()
    }
}