package com.example.shekinah.presentation.screen.listprays.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shekinah.domain.usecase.database.FirestoreUseCase
import com.example.shekinah.domain.usecase.firebaseauth.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListPrayViewModel(private val dbUseCase: FirestoreUseCase, private val auth: AuthUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(ListState())
    val state = _state

    init {
        getUserName()
        viewModelScope.launch {
            dbUseCase.recoverPray().collect { listPrays ->
                _state.update { currentState ->
                    currentState.copy(list = listPrays.toList())
                }
            }
        }

    }

    private fun getUserName() {
        val name = auth.getCurrentUserName()
        _state.update { it.copy(userName = name ?: "Usuário") }
    }

    fun logout() {
        viewModelScope.launch {
            auth.logout()
            getUserName()
        }
    }
}