package com.example.shekinah.domain

import com.example.shekinah.data.repository.RepositoryDb

class DbUseCaseImpl(private val repository: RepositoryDb) : DbUseCase {
    override suspend fun savePray(title: String, description: String) {
        return if (title.isNotEmpty() && description.isNotEmpty()) {
            repository.savePray(title, description)
        } else {

        }
    }
}