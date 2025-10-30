package com.example.shekinah.data.api.fireauth

import com.example.shekinah.data.model.AuthDto
import com.example.shekinah.data.model.RecoverDto

interface ApiService {
    suspend fun register( email: String, password: String): AuthDto

    suspend fun singIn(email: String, password: String): AuthDto

    suspend fun recoverPassword(email: String): RecoverDto









}