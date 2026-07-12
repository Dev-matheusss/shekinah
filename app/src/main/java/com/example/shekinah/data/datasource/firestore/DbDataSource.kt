package com.example.shekinah.data.datasource.firestore

import android.net.Uri
import androidx.compose.ui.text.LinkAnnotation
import com.example.shekinah.data.m.UserDto
import com.example.shekinah.data.model.PrayDto
import com.example.shekinah.data.model.WarningDto
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.Flow

interface DbDataSource {
    suspend fun saveWarning(warning: String, imageUri: Uri?)
    suspend fun updateProfile(newName: String, newUri: Uri?)
    suspend fun savePray(title:String, description: String, name: String)
    suspend fun recoverPray(): Flow<MutableList<PrayDto>>

    suspend fun recoverWarnings(): Flow<MutableList<WarningDto>>
    suspend fun recoverDetails(id: String): PrayDto?

    suspend fun fetchUserProfile(userId: String): UserDto
    suspend fun getCurrentId(): String?
}