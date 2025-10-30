package com.example.shekinah.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.shekinah.presentation.screen.praydetailsscreen.PrayDetailsViewModel
import com.example.shekinah.presentation.screen.praydetailsscreen.PrayRoute
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.prayDetailsScreen(
    navigateTo: (Any) -> Unit = {}
) {
    composable<PrayDetailsRouts> {
        val viewModel = koinViewModel<PrayDetailsViewModel>()
        val state = viewModel.screenDetailsState.collectAsState().value
        val args = it.toRoute<PrayDetailsRouts>()
        LaunchedEffect(args.id) {
            viewModel.recoverDetails(args.id)
        }

        PrayRoute(
            id = args.id,
            state = state)
    }

}