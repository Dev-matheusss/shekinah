package com.example.shekinah.data.datasource

import com.example.shekinah.data.api.model.AuthDto

interface DataSourceAuth {
    suspend fun register(email: String, password: String): AuthDto
    suspend fun singIn(email: String,password: String): AuthDto
}