package com.example.shekinah.domain.usecase.storage

import android.net.Uri

interface StorageUseCase {
    suspend fun uploadProfileImage(image: Uri): String?
}