package com.example.shekinah.data.repository

import com.example.shekinah.data.api.model.AuthDto

interface Repository {
    suspend fun register(email: String, password: String): AuthDto
    suspend fun singIn(email: String, password: String): AuthDto
}