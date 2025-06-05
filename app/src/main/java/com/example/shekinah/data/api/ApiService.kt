package com.example.shekinah.data.api

import com.example.shekinah.data.api.model.AuthDto

interface ApiService {
    suspend fun register(email: String, password: String): AuthDto
    suspend fun singIn(email: String, password: String): AuthDto
    suspend fun savePray(title: String, description: String)
}