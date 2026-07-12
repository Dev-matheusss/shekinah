package com.example.shekinah.domain.usecase.database

import com.example.shekinah.data.model.PrayDto
import com.example.shekinah.data.repository.firestore.FirestoreRepository
import kotlinx.coroutines.flow.Flow

class FirestoreUseCaseImpl(private val repository: FirestoreRepository) : FirestoreUseCase {
    override suspend fun savePray(title: String, description: String, name: String) {
        return if (title.isNotEmpty() && description.isNotEmpty()) {
            repository.savePray(title, description, name)
        } else {
        }
    }

    override suspend fun recoverPray(): Flow<MutableList<PrayDto>> {
        return repository.recoverPray()
    }

    override suspend fun recoverDetails(id: String): PrayDto? {
        return repository.recoverDetails(id)
    }
}