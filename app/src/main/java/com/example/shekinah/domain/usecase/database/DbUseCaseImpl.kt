package com.example.shekinah.domain.usecase.database

import com.example.shekinah.data.repository.database.RepositoryDb

class DbUseCaseImpl(private val repository: RepositoryDb) : DbUseCase {
    override suspend fun savePray(title: String, description: String) {
        return if (title.isNotEmpty() && description.isNotEmpty()) {
            repository.savePray(title, description)
        } else {

        }
    }
}