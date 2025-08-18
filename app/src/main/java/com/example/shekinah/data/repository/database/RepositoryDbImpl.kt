package com.example.shekinah.data.repository.database

import com.example.shekinah.data.datasource.firestoredatasource.DataSourceDb

class RepositoryDbImpl(private val dataSource: DataSourceDb) : RepositoryDb {
    override suspend fun savePray(title:String, description: String){
        return dataSource.savePray(title,description)
    }
}