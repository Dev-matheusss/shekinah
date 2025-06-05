package com.example.shekinah.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.loginscreen.LoginRoute

fun NavGraphBuilder.loginScreen(
    navigateTo: (Any) -> Unit = {}
) {
    composable<LoginScreenRouts> {
        LoginRoute(navigateTo)
    }
}

