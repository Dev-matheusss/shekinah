package com.example.shekinah.domain.usecase.database

import com.example.shekinah.data.model.Pray
import com.example.shekinah.data.repository.database.RepositoryDb
import kotlinx.coroutines.flow.Flow

class DbUseCaseImpl(private val repository: RepositoryDb) : DbUseCase {
    override suspend fun savePray(title: String, description: String) {
        return if (title.isNotEmpty() && description.isNotEmpty()) {
            repository.savePray(title, description)
        } else {

        }
    }

    override suspend fun recoverPray(): Flow<MutableList<Pray>> {
        return repository.recoverPray()
    }
}