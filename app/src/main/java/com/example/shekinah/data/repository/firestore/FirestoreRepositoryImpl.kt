package com.example.shekinah.data.repository.firestore

import com.example.shekinah.data.datasource.firestore.DbDataSource
import com.example.shekinah.data.model.PrayDto
import kotlinx.coroutines.flow.Flow

class FirestoreRepositoryImpl(private val dataSource: DbDataSource) : FirestoreRepository {
    override suspend fun savePray(title:String, description: String, name: String){
        return dataSource.savePray(title,description,name)
    }
    override suspend fun recoverPray(): Flow<MutableList<PrayDto>> {
        return dataSource.recoverPray()
    }

    override suspend fun recoverDetails(id: String): PrayDto? {
        return dataSource.recoverDetails(id)
    }
}