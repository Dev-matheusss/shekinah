package com.example.shekinah.presentation.screen.register.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shekinah.domain.usecase.firebaseauth.AuthUseCase
import com.example.shekinah.domain.usecase.storage.StorageUseCase
import com.example.shekinah.presentation.screen.profile.viewmodel.ProfileState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(val useCase: AuthUseCase, val storageUseCase: StorageUseCase) :
    ViewModel() {

    var registerState = MutableStateFlow(RegisterState())

    fun onImageSelected(uri: Uri) {
        registerState.update {
            it.copy(
                imageUri = uri,
                imageState = ProfileState.Success(photoUrl = uri.toString())
            )
        }
    }

    fun uploadProfileImage(uri: Uri) {
        viewModelScope.launch {
            registerState.update {
                it.copy(imageState = ProfileState.Loading,
                    imageUri = uri)
            }

            try {
                val url = storageUseCase.uploadProfileImage(uri)

                registerState.update {
                    it.copy(imageState = ProfileState.Success(photoUrl = url))
                }

            } catch (e: Exception) {
                registerState.update {
                    it.copy(imageState = ProfileState.Error("Erro upload"))
                }
            }
        }
    }

            fun register(email: String, password: String, name: String, imageUri: Uri) {
                viewModelScope.launch(Dispatchers.IO) {
                    val result = useCase.register(email, password, name, imageUri)
                    registerState.update {
                        it.copy(result = result)
                    }
                }
            }

            fun nameChange(name: String) {
                registerState.update {
                    it.copy(name = name)
                }
            }

            fun emailChange(email: String) {
                registerState.update {
                    it.copy(email = email)
                }
            }

            fun passwordChange(password: String) {
                registerState.update {
                    it.copy(password = password)
                }
            }
        }


