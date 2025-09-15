package com.example.shekinah.domain.usecase.database

import com.example.shekinah.data.model.Pray
import kotlinx.coroutines.flow.Flow

interface DbUseCase {
    suspend fun savePray(title:String, description: String)
    suspend fun recoverPray(): Flow<MutableList<Pray>>
}