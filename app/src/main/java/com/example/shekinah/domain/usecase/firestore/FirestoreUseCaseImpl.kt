package com.example.shekinah.domain.usecase.firestore

import android.net.Uri
import com.example.shekinah.data.m.UserDto
import com.example.shekinah.data.model.PrayDto
import com.example.shekinah.data.model.WarningDto
import com.example.shekinah.data.repository.firestore.FirestoreRepository
import com.example.shekinah.presentation.screen.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class FirestoreUseCaseImpl(private val repository: FirestoreRepository) : FirestoreUseCase {
    override suspend fun saveWarning(warning: String, imageUri: Uri?) {
        return repository.saveWarning(warning, imageUri)
    }

    override suspend fun updateProfile(newName: String, newUri: Uri?) {
        return repository.updateProfile(newName, newUri)
    }
    override suspend fun savePray(title: String, description: String, name: String) {
        return if (title.isNotEmpty() && description.isNotEmpty()) {
            repository.savePray(title, description, name)
        } else {
        }
    }

    override suspend fun recoverPray(): Flow<MutableList<PrayDto>> {
        return repository.recoverPray()
    }

    override suspend fun recoverWarnings(): Flow<MutableList<WarningDto>> {
        return repository.recoverWarnings()
    }

    override suspend fun recoverDetails(id: String): PrayDto? {
        return repository.recoverDetails(id)
    }

    override suspend fun fetchUserProfile(userId: String): Resource<UserDto> {
        val userDto = repository.fetchUserProfile(userId)
        return when {
            userDto.errorMessage != null -> {
                Resource.Error(userDto.errorMessage)
            }
            userDto.name.isNotBlank() -> {
                Resource.Success(userDto)
            }
            else -> {
                Resource.Error("Dados do perfil não encontrados")
            }
        }
    }

    override suspend fun getCurrentId(): String? {
        return repository.getCurrentId()
    }

}