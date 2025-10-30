package com.example.shekinah.data.repository.database

import com.example.shekinah.data.datasource.firestoredatasource.DataSourceDb
import com.example.shekinah.data.model.Pray
import kotlinx.coroutines.flow.Flow

class RepositoryDbImpl(private val dataSource: DataSourceDb) : RepositoryDb {
    override suspend fun savePray(title:String, description: String){
        return dataSource.savePray(title,description)
    }
    override suspend fun recoverPray(): Flow<MutableList<Pray>> {
        return dataSource.recoverPray()
    }

    override suspend fun recoverDetails(id: String): Pray? {
        return dataSource.recoverDetails(id)
    }
}