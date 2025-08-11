package com.example.shekinah.data.datasource

interface DataSourceDb {
    suspend fun savePray(title:String, description: String)
}