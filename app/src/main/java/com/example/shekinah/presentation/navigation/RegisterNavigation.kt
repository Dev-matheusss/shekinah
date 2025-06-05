package com.example.shekinah.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.registerscreen.RegisterRoute

fun NavGraphBuilder.createAcountScreen(
    navigateTo:(Any)-> Unit ={}
){
    composable<CreateAcountRouts>{
        RegisterRoute (navigateTo)
    }

}