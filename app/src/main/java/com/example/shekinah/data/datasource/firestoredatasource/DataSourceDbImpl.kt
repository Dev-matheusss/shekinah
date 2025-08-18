package com.example.shekinah.data.datasource.firestoredatasource

import com.example.shekinah.data.api.firestore.FireStore

class DataSourceDbImpl(private val firestore: FireStore) : DataSourceDb {
    override suspend fun savePray(title: String, description: String) {
       return firestore.savePray(title,description)
    }
}