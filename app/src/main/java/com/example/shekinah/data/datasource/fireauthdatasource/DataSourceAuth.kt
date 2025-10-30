package com.example.shekinah.data.datasource.fireauthdatasource

import com.example.shekinah.data.model.AuthDto
import com.example.shekinah.data.model.RecoverDto

interface DataSourceAuth {
    suspend fun register(email: String, password: String): AuthDto
    suspend fun singIn(email: String,password: String): AuthDto

    suspend fun recoverPassword(email: String): RecoverDto
}