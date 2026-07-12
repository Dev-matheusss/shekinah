package com.example.shekinah.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.shekinah.presentation.navigation.createAcountScreen
import com.example.shekinah.presentation.navigation.listScreen
import com.example.shekinah.presentation.navigation.loginScreen
import com.example.shekinah.presentation.navigation.placeorderscren
import com.example.shekinah.presentation.navigation.prayDetailsScreen
import com.example.shekinah.presentation.navigation.profileScreen
import com.example.shekinah.presentation.navigation.recoverPasswordScreen
import com.example.shekinah.presentation.navigation.warningsScreen

@Composable
fun ShekinahNavHost(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = LoginScreenRout
    ) {
        listScreen(
            navigateTo = { prayId ->
                navController.navigate(prayId)
            }
        )
        loginScreen(
            navigateTo = { Any ->
                navController.navigate(Any) {

                }
            }
        )
        placeorderscren(
            navigateTo = { Any ->
                navController.navigate(route = Any) {
                    popUpTo(0)
                }
            }
        )
        createAcountScreen(
            navigateTo = { Any ->
                navController.navigate(Any) {
                }
            }
        )
        prayDetailsScreen(
            navigateTo = {
            }
        )
        recoverPasswordScreen(
        )
        profileScreen(
            navigateTo = {

            }
        )

        warningsScreen(
            navigateTo = {
                Any ->
                navController.navigate(Any)

            }
        )

        createWarningScreen(
            navigateTo ={
                Any ->
                navController.navigate(Any){
                    popUpTo(ListPrayScreenRout)
                }

            }
        )
    }

}