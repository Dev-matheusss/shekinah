package com.example.shekinah.data.datasource.fireauthdatasource

import com.example.shekinah.data.model.AuthDto

interface DataSourceAuth {
    suspend fun register(email: String, password: String): AuthDto
    suspend fun singIn(email: String,password: String): AuthDto
}