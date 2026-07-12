package com.example.shekinah.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.warnings.CreateWarningRoute
import com.example.shekinah.presentation.screen.warnings.viewmodel.CreateWarningViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.createWarningScreen(
    navigateTo: (Any)-> Unit = {}
){
    composable < CreateWarningScreenRout>{
        val viewModel = koinViewModel <CreateWarningViewModel>()
        val state = viewModel.state.collectAsState().value
        CreateWarningRoute(
            state = state,
            warningChange = {viewModel.warningChange(it)},
            onClickSaveWarning = {warning, imageUri ->
            viewModel.saveWarning(warning, imageUri)},
            navigateTo = navigateTo
        )
    }
}