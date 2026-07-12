package com.example.shekinah.data.datasource.firestore

import android.net.Uri
import com.example.shekinah.data.api.ApiService
import com.example.shekinah.data.m.UserDto
import com.example.shekinah.data.model.PrayDto
import com.example.shekinah.data.model.WarningDto
import kotlinx.coroutines.flow.Flow

class DbDataSourceImpl(private val firestore: ApiService) : DbDataSource {
    override suspend fun saveWarning(warning: String, imageUri: Uri?) {
        return firestore.saveWarning(warning, imageUri)
    }

    override suspend fun updateProfile(newName: String, newUri: Uri?){
        return firestore.updateProfile(newName,newUri)
    }
    override suspend fun savePray(title: String, description: String, name: String) {
        return firestore.savePray(title, description, name )
    }

    override suspend fun recoverPray(): Flow<MutableList<PrayDto>> {
        return firestore.recoverPray()
    }

    override suspend fun recoverWarnings(): Flow<MutableList<WarningDto>> {
        return firestore.recoverWarnings()
    }

    override suspend fun recoverDetails(id: String): PrayDto? {
        return firestore.recoverDetails(id)
    }

    override suspend fun fetchUserProfile(userId: String): UserDto {
        return firestore.fetchUserProfile(userId)
    }

    override suspend fun getCurrentId(): String? {
        return firestore.getCurrentId()
    }
}