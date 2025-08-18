package com.example.shekinah.data.repository.database

interface RepositoryDb {
    suspend fun savePray(title:String, description:String)
}