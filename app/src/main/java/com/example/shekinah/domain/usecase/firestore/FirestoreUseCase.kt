package com.example.shekinah.domain.usecase.database

import com.example.shekinah.data.model.PrayDto
import kotlinx.coroutines.flow.Flow

interface FirestoreUseCase {
    suspend fun savePray(title:String, description: String, name: String)
    suspend fun recoverPray(): Flow<MutableList<PrayDto>>

    suspend fun recoverDetails(id: String): PrayDto?
}