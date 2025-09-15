package com.example.shekinah.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.listprayscreen.ListRoute
import com.example.shekinah.presentation.screen.listprayscreen.ListPrayViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.listScreen(
    navigateTo: (Any) -> Unit = {}
) {
    composable<ListPrayRouts> {
        val viewModel = koinViewModel<ListPrayViewModel>()
        val state = viewModel.state.collectAsState().value
        ListRoute(onClickDetails = {
            navigateTo(PrayDetailsRouts)}, navigateTo ,state = state)
    }
}



