package com.example.shekinah.data.datasource.firestoredatasource

import com.example.shekinah.data.api.firestore.FireStore
import com.example.shekinah.data.model.Pray
import kotlinx.coroutines.flow.Flow

class DataSourceDbImpl(private val firestore: FireStore) : DataSourceDb {
    override suspend fun savePray(title: String, description: String) {
       return firestore.savePray(title,description)
    }
    override suspend fun recoverPray(): Flow<MutableList<Pray>>{
        return firestore.recoverPray()
    }

    override suspend fun recoverDetails(id: String): Pray? {
        return firestore.recoverDetails(id)
    }
}