package com.example.shekinah.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.placeorder.PlaceOrderRout

fun NavGraphBuilder.placeorderscren(
    navigateTo : (Any) -> Unit = {}
){

    composable<PlaceOrderScreenRout>{
        PlaceOrderRout(navigateTo)
    }
}


