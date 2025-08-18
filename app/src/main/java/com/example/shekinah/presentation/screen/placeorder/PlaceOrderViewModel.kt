package com.example.shekinah.presentation.screen.placeorder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shekinah.domain.usecase.database.DbUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaceOrderViewModel(private val useCase: DbUseCase) : ViewModel() {
    var placeOrderState = MutableStateFlow(PlaceOrderState())
    fun savePray(title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.savePray(title, description)
        }
    }

    fun titleChange(title: String) {
        placeOrderState.update {
            it.copy(title = title)
        }
    }

    fun descriptionChange(description: String){
        placeOrderState.update {
            it.copy(description = description)
        }
    }
}

