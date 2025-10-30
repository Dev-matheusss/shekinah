package com.example.shekinah.presentation.screen.recoverPassword

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shekinah.domain.usecase.firebaseauth.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecoverPasswordViewModel(private val useCase: AuthUseCase): ViewModel() {
    private val _state = MutableStateFlow(RecoverPasswordState())
    val state: StateFlow<RecoverPasswordState> = _state

    fun recoverPassword(email: String){
        viewModelScope.launch {
            try {
                val (success, message) = useCase.recoverPassword(email)
                _state.value = RecoverPasswordState(
                    success = success,
                    message = message)
            }catch (e: Exception){
                _state.value = RecoverPasswordState(
                    success = false,
                    message = e.localizedMessage ?: "Erro desconhecido")
            }

        }
    }
    private fun updateState(state: RecoverPasswordState){
        _state.value = state
    }
    fun emailChange(email: String){
        updateState(state = _state.value.copy(email = email))
    }

}