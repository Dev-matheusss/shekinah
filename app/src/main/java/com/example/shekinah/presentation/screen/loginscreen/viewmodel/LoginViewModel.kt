package com.example.shekinah.presentation.screen.loginscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shekinah.domain.ShekinahUseCase
import com.example.shekinah.presentation.screen.loginscreen.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val usecase: ShekinahUseCase): ViewModel() {
    var loginState = MutableStateFlow(LoginState("", ""))
    fun singIn(email: String, password: String) {
        viewModelScope.launch {
            val result = usecase.singIn(email, password)
            updateState(state = loginState.value.copy(result = result))
        }
    }

    fun emailChange(email: String) {
        updateState(state = loginState.value.copy(email = email))
    }

    fun passwordChange(password: String) {
        updateState(state = loginState.value.copy(password = password))
    }

    private fun updateState(state: LoginState) {
        loginState.value = state
    }
}