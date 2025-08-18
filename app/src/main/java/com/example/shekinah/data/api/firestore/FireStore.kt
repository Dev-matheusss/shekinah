package com.example.shekinah.data.api.firestore

interface FireStore {
    suspend fun savePray(title: String, description: String)
}