package com.example.shekinah.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.placeorder.PlaceOrderRout

fun NavGraphBuilder.placeorderscren(
    navigateTo : (Any) -> Unit = {}
){
    composable<PlaceOrderRouts>{
        PlaceOrderRout(navigateTo)
    }
}


