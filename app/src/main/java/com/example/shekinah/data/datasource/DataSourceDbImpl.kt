package com.example.shekinah.data.datasource

import com.example.shekinah.data.api.FireStore

class DataSourceDbImpl(private val firestore: FireStore) : DataSourceDb {
    override suspend fun savePray(title: String, description: String) {
       return firestore.savePray(title,description)
    }
}