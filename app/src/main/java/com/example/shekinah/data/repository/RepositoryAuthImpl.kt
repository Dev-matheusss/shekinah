package com.example.shekinah.data.repository

import com.example.shekinah.data.api.ApiService
import com.example.shekinah.data.api.model.AuthDto

class RepositoryAuthImpl(private val api: ApiService): RepositoryAuth {
    override suspend fun register(email: String, password: String): AuthDto {
        return api.register(email,password)
    }

    override suspend fun singIn(email: String, password: String): AuthDto {
        return api.singIn(email,password)
    }
}