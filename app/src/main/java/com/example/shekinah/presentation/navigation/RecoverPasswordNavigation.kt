package com.example.shekinah.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.recoverPassword.RecoverPasswordRoute
import com.example.shekinah.presentation.screen.recoverPassword.RecoverPasswordViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.recoverPasswordScreen(
    navigateTo: (Any) -> Unit = {}
) {
    composable<RecoverPasswordRouts> {
        val viewModel = koinViewModel<RecoverPasswordViewModel>()
        val state = viewModel.state.collectAsState().value
        RecoverPasswordRoute(
            navigateTo = navigateTo,
            state = state,
            emailChange = { viewModel.emailChange(it) },
            onClickRecover = { email ->
                viewModel.recoverPassword(email)
            })
    }

}