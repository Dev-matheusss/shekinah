package com.example.shekinah.data.datasource.fireauth

import android.net.Uri
import com.example.shekinah.data.model.AuthDto
import com.example.shekinah.data.model.RecoverDto

interface AuthDataSource {
    suspend fun register(email: String, password: String, name: String, imageUri: Uri): AuthDto

    suspend fun singIn(email: String, password: String): AuthDto

    suspend fun recoverPassword(email: String): RecoverDto

    suspend fun logout()
}