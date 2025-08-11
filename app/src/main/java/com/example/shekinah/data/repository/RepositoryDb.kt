package com.example.shekinah.data.repository

interface RepositoryDb {
    suspend fun savePray(title:String, description:String)
}