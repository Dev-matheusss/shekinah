package com.example.shekinah.data.api

import android.net.Uri
import com.example.shekinah.data.m.UserDto
import com.example.shekinah.data.model.AuthDto
import com.example.shekinah.data.model.PrayDto
import com.example.shekinah.data.model.RecoverDto
import com.example.shekinah.data.model.WarningDto
import com.example.shekinah.presentation.screen.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ApiService {
    //funções de update.
    suspend fun updateProfile(newName: String, newUri: Uri?)

    //funções de save.
    suspend fun uploadProfileImage(image: Uri): String?
    suspend fun savePray(title: String, description: String, name: String)
    suspend fun saveWarning(warning: String, imageUri: Uri?)

    //funções de recuperação.
    suspend fun fetchUserProfile(userId: String): UserDto
    suspend fun recoverPassword(email: String): RecoverDto
    suspend fun recoverPray(): Flow<MutableList<PrayDto>>

    suspend fun recoverWarnings(): Flow<MutableList<WarningDto>>
    suspend fun recoverDetails(id: String): PrayDto?
    suspend fun getCurrentId(): String?

    //funções de autenticação.
    suspend fun logout()
    suspend fun register( email: String, password: String, name: String, imageUri: Uri?): AuthDto

    suspend fun singIn(email: String, password: String): AuthDto
}

