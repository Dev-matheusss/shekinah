package com.example.shekinah.presentation.screen.warnings.viewmodel.state

import com.example.shekinah.data.model.WarningDto

class ListWarningsState (
    val list: List<WarningDto> = emptyList(),
    val warnings: String = ""
)