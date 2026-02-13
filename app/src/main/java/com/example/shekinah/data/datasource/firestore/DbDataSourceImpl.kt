package com.example.shekinah.data.datasource.firestore

import com.example.shekinah.data.api.ApiService
import com.example.shekinah.data.model.PrayDto
import kotlinx.coroutines.flow.Flow

class DbDataSourceImpl(private val firestore: ApiService) : DbDataSource {
    override suspend fun savePray(title: String, description: String, name: String) {
        return firestore.savePray(title, description, name )
    }

    override suspend fun recoverPray(): Flow<MutableList<PrayDto>> {
        return firestore.recoverPray()
    }

    override suspend fun recoverDetails(id: String): PrayDto? {
        return firestore.recoverDetails(id)
    }
}