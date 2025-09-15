package com.example.shekinah.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.praydetailsscreen.PrayRoute

fun NavGraphBuilder.prayDetailsScreen(
    navigateTo: (Any) -> Unit = {}
){
    composable <PrayDetailsRouts>{
        PrayRoute()

    }

}