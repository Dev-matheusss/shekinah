package com.example.shekinah.presentation.screen.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shekinah.domain.usecase.storage.StorageUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val useCase: StorageUseCase) : ViewModel() {
    private val _profileImageState = MutableStateFlow<ProfileImageState>(ProfileImageState.Idle)
    val profileImageState: StateFlow<ProfileImageState> = _profileImageState

    init {
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
            _profileImageState.value = ProfileImageState.Loading

            try {
                val user = FirebaseAuth.getInstance().currentUser
                val userId = user?.uid

                if (userId != null) {
                    val db = FirebaseFirestore.getInstance()
                    val userDoc = db.collection("users").document(userId)

                    userDoc.get().addOnSuccessListener { document ->
                        if (document != null && document.exists()) {
                            val name = document.getString("name") ?: ""
                            val email = document.getString("email") ?: ""
                            val photoUrl = document.getString("photoUrl") ?: ""

                            // Atualizar o estado com os dados recuperados
                            _profileImageState.value = ProfileImageState.Success(
                                name = name,
                                email = email,
                                photoUrl = photoUrl
                            )
                        } else {
                            _profileImageState.value =
                                ProfileImageState.Error("Usuário não encontrado")
                        }
                    }.addOnFailureListener {
                        _profileImageState.value =
                            ProfileImageState.Error(it.message ?: "Erro ao buscar dados")
                    }
                } else {
                    _profileImageState.value = ProfileImageState.Error("Usuário não autenticado")
                }
            } catch (e: Exception) {
                _profileImageState.value = ProfileImageState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }


    fun uploadProfileImage(image: Uri) {
        viewModelScope.launch {
            _profileImageState.value = ProfileImageState.Loading

            try {
                val url = useCase.uploadProfileImage(image)

                if (url != null) {
                    val currentState = _profileImageState.value
                    if (currentState is ProfileImageState.Success) {
                        _profileImageState.value = ProfileImageState.Success(
                            name = currentState.name,
                            email = currentState.email,
                            photoUrl = url
                        )
                    } else {
                        _profileImageState.value = ProfileImageState.Success(
                            name = "",
                            email = "",
                            photoUrl = url
                        )
                    }
                } else {
                    _profileImageState.value = ProfileImageState.Error("Falha no upload")
                }

            } catch (e: Exception) {
                _profileImageState.value = ProfileImageState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}