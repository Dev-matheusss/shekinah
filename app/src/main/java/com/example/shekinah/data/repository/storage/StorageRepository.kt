package com.example.shekinah.data.repository.storage

import android.net.Uri

interface StorageRepository {
    suspend fun uploadProfileImage(image: Uri): String?
}