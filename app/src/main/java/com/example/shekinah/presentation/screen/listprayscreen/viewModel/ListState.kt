package com.example.shekinah.presentation.screen.listprayscreen.viewModel

import androidx.compose.ui.graphics.vector.EmptyPath
import com.example.shekinah.data.model.Pray

data class ListState (
   val list : List<Pray> = emptyList(),

   )