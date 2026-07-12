package com.example.shekinah.presentation.screen.praydetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shekinah.domain.usecase.firestore.FirestoreUseCase
import com.example.shekinah.presentation.screen.praydetails.viewmodel.ScreenDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PrayDetailsViewModel(private val dbUseCase: FirestoreUseCase): ViewModel() {
    var screenDetailsState = MutableStateFlow(ScreenDetailsState())
    fun recoverDetails(id: String){
        viewModelScope.launch {
            val pray = dbUseCase.recoverDetails(id)
            screenDetailsState.value = screenDetailsState.value.copy( pray = pray)
        }
    }

        }