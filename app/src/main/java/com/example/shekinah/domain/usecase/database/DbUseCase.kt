package com.example.shekinah.domain.usecase.database

interface DbUseCase {
    suspend fun savePray(title:String, description: String)
}