package com.example.shekinah.data.api.firestore

import com.example.shekinah.data.model.Pray
import kotlinx.coroutines.flow.Flow

interface FireStore {
    suspend fun savePray(title: String, description: String)
    suspend fun recoverPray(): Flow<MutableList<Pray>>

    suspend fun recoverDetails(id: String): Pray?


}