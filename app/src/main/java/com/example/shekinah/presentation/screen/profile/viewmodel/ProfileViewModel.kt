package com.example.shekinah.presentation.screen.profile.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shekinah.domain.usecase.firestore.FirestoreUseCase
import com.example.shekinah.domain.usecase.storage.StorageUseCase
import com.example.shekinah.presentation.screen.ui.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel( private val firestoreUseCase: FirestoreUseCase) : ViewModel() {
    private val _profileState = MutableStateFlow<ProfileState>(ProfileState.Idle)
    val profileState: StateFlow<ProfileState> = _profileState
    init {
        fetchUserProfile()
    }

    fun updateProfile(newName: String, newUri: Uri?) {
        viewModelScope.launch {
            _profileState.value = ProfileState.Loading
            try {
                firestoreUseCase.updateProfile(newName, newUri)
                fetchUserProfile()
            } catch (e: Exception) {
                _profileState.value = ProfileState.Error(e.message ?: "Erro ao atualizar")
            }
        }
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
            _profileState.value = ProfileState.Loading

            val userId = firestoreUseCase.getCurrentId()

            if (userId != null) {
                val result = firestoreUseCase.fetchUserProfile(userId)
                _profileState.value = when (result) {
                    is Resource.Success -> ProfileState.Success(
                        name = result.data.name,
                        photoUrl = result.data.photoUrl
                    )
                    is Resource.Error -> ProfileState.Error(result.message)
                    is Resource.Loading -> ProfileState.Loading
                }
            }
        }
    }
}