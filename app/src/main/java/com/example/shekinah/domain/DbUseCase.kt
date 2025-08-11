package com.example.shekinah.domain

interface DbUseCase {
    suspend fun savePray(title:String, description: String)
}