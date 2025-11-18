package com.example.shekinah.presentation.screen.listprayscreen

import android.view.View
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shekinah.data.api.fireauth.ApiService
import com.example.shekinah.data.model.Pray
import com.example.shekinah.domain.usecase.database.DbUseCase
import com.example.shekinah.domain.usecase.firebaseauth.AuthUseCase
import com.example.shekinah.presentation.screen.listprayscreen.viewModel.ListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListPrayViewModel(private val dbUseCase: DbUseCase, private val auth: AuthUseCase) : ViewModel() {

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
}