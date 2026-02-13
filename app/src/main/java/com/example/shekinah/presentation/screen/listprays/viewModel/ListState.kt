package com.example.shekinah.presentation.screen.listprays.viewModel

import com.example.shekinah.data.model.PrayDto

data class ListState (
    val list : List<PrayDto> = emptyList(),
    val userName: String = ""

   )