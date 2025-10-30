package com.example.shekinah.data.datasource.firestoredatasource

import com.example.shekinah.data.model.Pray
import kotlinx.coroutines.flow.Flow

interface DataSourceDb {
    suspend fun savePray(title:String, description: String)
    suspend fun recoverPray(): Flow<MutableList<Pray>>

    suspend fun recoverDetails(id: String): Pray?
}