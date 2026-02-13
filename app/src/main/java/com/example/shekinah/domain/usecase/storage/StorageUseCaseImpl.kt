package com.example.shekinah.domain.usecase.storage

import android.net.Uri
import com.example.shekinah.data.repository.storage.StorageRepository

class StorageUseCaseImpl(val repository: StorageRepository): StorageUseCase {
    override suspend fun uploadProfileImage(image: Uri): String?{
        return repository.uploadProfileImage(image)
    }
}