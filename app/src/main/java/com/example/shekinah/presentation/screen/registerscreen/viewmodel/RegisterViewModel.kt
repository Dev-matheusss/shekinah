package com.example.shekinah.presentation.screen.registerscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shekinah.domain.usecase.firebaseauth.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(val useCase: AuthUseCase) : ViewModel() {

    var registerState = MutableStateFlow(RegisterState())

    fun register(email: String, password: String){
            viewModelScope.launch(Dispatchers.IO) {
                val result = useCase.register(email, password)
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
