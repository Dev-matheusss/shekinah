package com.example.shekinah.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.login.LoginRoute

fun NavGraphBuilder.loginScreen(
    navigateTo: (Any) -> Unit = {}
) {
    composable<LoginScreenRout> {
        LoginRoute(navigateTo)
    }
}

