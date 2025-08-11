package com.example.shekinah.data.datasource

import com.example.shekinah.data.api.FireStore

class DataSourceDbImpl(private val Fs: FireStore) : DataSourceDb {
    override suspend fun savePray(title: String, description: String) {
        return Fs.savePray(title, description)

    }
}