package com.example.shekinah.data.repository

import com.example.shekinah.data.api.FireStore
import com.example.shekinah.data.datasource.DataSourceDb

class RepositoryDbImpl(private val dataSource: DataSourceDb) : RepositoryDb {
    override suspend fun savePray(title:String, description: String){
        return dataSource.savePray(title,description)
    }
}