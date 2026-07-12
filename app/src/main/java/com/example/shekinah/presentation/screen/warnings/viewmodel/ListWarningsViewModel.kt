package com.example.shekinah.presentation.screen.warnings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shekinah.domain.usecase.firestore.FirestoreUseCase
import com.example.shekinah.presentation.screen.warnings.viewmodel.state.ListWarningsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListWarningsViewModel(private val useCase: FirestoreUseCase): ViewModel() {
    private val _state = MutableStateFlow(ListWarningsState())
    val state = _state

    init {
        viewModelScope.launch(Dispatchers.IO){
            useCase.recoverWarnings().collect { listWarnings ->
                _state.update { currentState ->
                    currentState.copy(list = listWarnings.toList())
                }
            }
        }

    }

}