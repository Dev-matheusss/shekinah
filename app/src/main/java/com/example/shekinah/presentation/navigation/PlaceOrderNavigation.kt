package com.example.shekinah.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.placeorder.PlaceOrderRout
import com.example.shekinah.presentation.screen.placeorder.PlaceOrderViewModel
import com.example.shekinah.presentation.screen.praydetailsscreen.PrayDetailsViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.placeorderscren(
    navigateTo : (Any) -> Unit = {}
){

    composable<PlaceOrderRouts>{
        PlaceOrderRout(navigateTo)
    }
}


