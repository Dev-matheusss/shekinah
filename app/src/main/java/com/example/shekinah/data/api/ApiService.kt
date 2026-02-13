package com.example.shekinah.data.api

import android.net.Uri
import com.example.shekinah.data.model.AuthDto
import com.example.shekinah.data.model.PrayDto
import com.example.shekinah.data.model.RecoverDto
import kotlinx.coroutines.flow.Flow

interface ApiService {
    suspend fun register( email: String, password: String, name: String, imageUri: Uri?): AuthDto

    suspend fun singIn(email: String, password: String): AuthDto

    suspend fun recoverPassword(email: String): RecoverDto

    suspend fun savePray(title: String, description: String, name: String)

    suspend fun recoverPray(): Flow<MutableList<PrayDto>>

    suspend fun recoverDetails(id: String): PrayDto?

    suspend fun logout()

    suspend fun uploadProfileImage(image: Uri): String?
}