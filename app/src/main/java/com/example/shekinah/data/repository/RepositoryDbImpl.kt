package com.example.shekinah.data.repository

import com.example.shekinah.data.api.FireStore

class RepositoryDbImpl(private val firestore: FireStore) : RepositoryDb {
    override suspend fun savePray(title:String, description: String){
        return firestore.savePray(title,description)
    }
}