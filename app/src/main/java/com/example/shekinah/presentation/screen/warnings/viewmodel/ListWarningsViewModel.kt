package com.example.shekinah.presentation.screen.warnings.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shekinah.domain.usecase.firestore.FirestoreUseCase
import com.example.shekinah.presentation.screen.listprays.viewmodel.ListState
import com.example.shekinah.presentation.screen.warnings.viewmodel.state.ListWarningsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WarningsViewModel(private val useCase: FirestoreUseCase): ViewModel() {
    private val _state = MutableStateFlow(ListWarningsState())
    val state = _state

}