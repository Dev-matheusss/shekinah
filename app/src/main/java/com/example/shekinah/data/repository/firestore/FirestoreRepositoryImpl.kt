package com.example.shekinah.data.repository.firestore

import android.net.Uri
import com.example.shekinah.data.datasource.firestore.DbDataSource
import com.example.shekinah.data.m.UserDto
import com.example.shekinah.data.model.AuthDto
import com.example.shekinah.data.model.PrayDto
import com.example.shekinah.data.model.WarningDto
import kotlinx.coroutines.flow.Flow

class FirestoreRepositoryImpl(private val dataSource: DbDataSource) : FirestoreRepository {
    override suspend fun saveWarning(warning: String, imageUri: Uri?) {
        return dataSource.saveWarning(warning, imageUri)
    }

    override suspend fun updateProfile(newName: String, newUri: Uri?){
        return dataSource.updateProfile(newName, newUri)
    }
    override suspend fun savePray(title: String, description: String, name: String) {
        return dataSource.savePray(title, description, name)
    }

    override suspend fun recoverPray(): Flow<MutableList<PrayDto>> {
        return dataSource.recoverPray()
    }

    override suspend fun recoverWarnings(): Flow<MutableList<WarningDto>> {
        return dataSource.recoverWarnings()
    }

    override suspend fun recoverDetails(id: String): PrayDto? {
        return dataSource.recoverDetails(id)
    }

    override suspend fun fetchUserProfile(userId: String): UserDto {
        return dataSource.fetchUserProfile(userId)
    }

    override suspend fun getCurrentId(): String? {
        return dataSource.getCurrentId()
    }
}