package com.example.shekinah.presentation.screen.warnings.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.shekinah.domain.usecase.firestore.FirestoreUseCase
import com.example.shekinah.presentation.screen.profile.viewmodel.ProfileState
import com.example.shekinah.presentation.screen.warnings.viewmodel.state.WarningState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateWarningViewModel(private val useCase: FirestoreUseCase) : ViewModel() {
    private var _state = MutableStateFlow(WarningState())
    val state = _state
    fun saveWarning(warning: String, imageUri: Uri?) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.saveWarning(warning, imageUri)
        }
    }

    fun warningChange(warning: String){
        _state.update {
            it.copy(warning = warning)
        }
        }
    }