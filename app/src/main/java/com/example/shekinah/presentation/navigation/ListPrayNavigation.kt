package com.example.shekinah.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.listprays.ListRoute
import com.example.shekinah.presentation.screen.listprays.viewModel.ListPrayViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.listScreen(
    navigateTo: (Any) -> Unit = {},
) {
    composable<ListPrayScreenRout> {
        val viewModel = koinViewModel<ListPrayViewModel>()
        val state = viewModel.state.collectAsState().value
        ListRoute(
            onClickDetails = { pray ->
                navigateTo(PrayDetailsScreenRout(
                    pray.id
                ))
            },
            navigateTo = {
               navigateTo(it)
            },
            state = state
        )
    }
}



