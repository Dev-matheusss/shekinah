package com.example.shekinah.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shekinah.data.ProviderListData
import com.example.shekinah.presentation.navigation.CreateAcountRouts
import com.example.shekinah.presentation.navigation.ListPrayRouts
import com.example.shekinah.presentation.navigation.LoginScreenRouts
import com.example.shekinah.presentation.navigation.createAcountScreen
import com.example.shekinah.presentation.navigation.loginScreen
import com.example.shekinah.presentation.screen.registerscreen.CreateAcountScreen
import com.example.shekinah.presentation.screen.listprayscreen.ListPrayScreen
import com.example.shekinah.presentation.screen.loginscreen.LoginScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val list = ProviderListData().listaDePosts
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = LoginScreenRouts
            ) {
                loginScreen(
                    navigateTo = { Any ->
                        navController.navigate(Any)
                    }
                )

                createAcountScreen(
                    navigateTo = { Any ->
                        navController.navigate(Any)
                    }
                )


                composable<ListPrayRouts> {
                    ListPrayScreen(onClick = {}, list)
                }

            }


        }


    }
}