package com.example.shekinah.data.datasource.firestoredatasource

interface DataSourceDb {
    suspend fun savePray(title:String, description: String)
}