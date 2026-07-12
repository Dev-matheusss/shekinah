package com.example.shekinah.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.warnings.WarningsRoute
import com.example.shekinah.presentation.screen.warnings.viewmodel.ListWarningsViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.warningsScreen(
    navigateTo: (Any)-> Unit = {}
){
    composable<WarningsScreenRout>{
        val viewModel = koinViewModel<ListWarningsViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        WarningsRoute(
            navigateTo = navigateTo,
            state = state
        )
    }
}