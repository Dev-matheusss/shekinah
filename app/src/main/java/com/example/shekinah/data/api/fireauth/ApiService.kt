package com.example.shekinah.data.api.fireauth

import com.example.shekinah.data.model.AuthDto

interface ApiService {
    suspend fun register( email: String, password: String): AuthDto

    suspend fun singIn(email: String, password: String): AuthDto

}