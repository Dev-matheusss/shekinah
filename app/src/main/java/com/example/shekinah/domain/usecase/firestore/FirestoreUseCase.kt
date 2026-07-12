package com.example.shekinah.domain.usecase.firestore

import android.net.Uri
import com.example.shekinah.data.m.UserDto
import com.example.shekinah.data.model.PrayDto
import com.example.shekinah.data.model.WarningDto
import com.example.shekinah.presentation.screen.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FirestoreUseCase {
    suspend fun saveWarning(warning: String, imageUri: Uri?)
    suspend fun updateProfile(newName: String, newUri: Uri?)
    suspend fun savePray(title:String, description: String, name: String)
    suspend fun recoverPray(): Flow<MutableList<PrayDto>>
    suspend fun recoverWarnings(): Flow<MutableList<WarningDto>>

    suspend fun recoverDetails(id: String): PrayDto?
    suspend fun fetchUserProfile(userId: String): Resource<UserDto>
    suspend fun getCurrentId(): String?

}