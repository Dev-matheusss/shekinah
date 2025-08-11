package com.example.shekinah.data.api

interface FireStore {
    suspend fun savePray(title: String, description: String)
}