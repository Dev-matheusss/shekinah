package com.example.shekinah.data.datasource.storage

import android.net.Uri

interface StorageDataSource {
    suspend fun uploadProfileImage(image: Uri): String?
}