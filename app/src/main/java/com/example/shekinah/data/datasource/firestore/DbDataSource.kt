package com.example.shekinah.data.datasource.firestore

import androidx.compose.ui.text.LinkAnnotation
import com.example.shekinah.data.model.PrayDto
import kotlinx.coroutines.flow.Flow

interface DbDataSource {
    suspend fun savePray(title:String, description: String, name: String)
    suspend fun recoverPray(): Flow<MutableList<PrayDto>>

    suspend fun recoverDetails(id: String): PrayDto?
}