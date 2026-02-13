package com.example.shekinah.data.repository.storage

import android.media.Image
import android.net.Uri
import com.example.shekinah.data.datasource.storage.StorageDataSource

class StorageRepositoryImpl(val dataSource: StorageDataSource): StorageRepository {
    override suspend fun uploadProfileImage(image: Uri): String?{
        return dataSource.uploadProfileImage(image)
    }
}