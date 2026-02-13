package com.example.shekinah.data.datasource.storage

import android.net.Uri
import com.example.shekinah.data.api.ApiService

class StorageDataSourceImpl(val storage: ApiService): StorageDataSource {
    override suspend fun uploadProfileImage(image: Uri): String? {
        return storage.uploadProfileImage(image)
    }
}