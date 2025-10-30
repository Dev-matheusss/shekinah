package com.example.shekinah.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shekinah.data.model.Pray
import com.example.shekinah.presentation.navigation.CreateAcountRouts
import com.example.shekinah.presentation.navigation.ListPrayRouts
import com.example.shekinah.presentation.navigation.LoginScreenRouts
import com.example.shekinah.presentation.navigation.PlaceOrderRouts
import com.example.shekinah.presentation.navigation.RecoverPasswordRouts
import com.example.shekinah.presentation.navigation.createAcountScreen
import com.example.shekinah.presentation.navigation.listScreen
import com.example.shekinah.presentation.navigation.loginScreen
import com.example.shekinah.presentation.navigation.placeorderscren
import com.example.shekinah.presentation.navigation.prayDetailsScreen
import com.example.shekinah.presentation.navigation.recoverPasswordScreen
import com.example.shekinah.presentation.screen.listprayscreen.ListPrayScreen
import com.example.shekinah.presentation.screen.listprayscreen.viewModel.ListState
import com.example.shekinah.presentation.screen.loginscreen.LoginState
import com.example.shekinah.presentation.screen.placeorder.PlaceOrderRout


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = LoginScreenRouts
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
            }
        }
    }
}