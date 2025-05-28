package com.example.shekinah.domain

import com.example.shekinah.data.repository.Repository
import com.example.shekinah.domain.model.Auth

class ShekinahUseCaseImpl(private val repository: Repository) : ShekinahUseCase {
    override suspend fun register(email: String, password: String): Auth {
        val result = repository.register(email, password)


        return TODO("Provide the return value")
    }


override suspend fun singIn(
    email: String, password: String
): Auth {
    TODO("Not yet implemented")
}
}