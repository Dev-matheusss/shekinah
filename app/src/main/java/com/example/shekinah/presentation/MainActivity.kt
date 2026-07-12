package com.example.shekinah.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.shekinah.presentation.navigation.ListPrayScreenRout
import com.example.shekinah.presentation.navigation.LoginScreenRout
import com.example.shekinah.presentation.navigation.RegisterScreenRout
import com.example.shekinah.presentation.navigation.ShekinahNavHost
import com.example.shekinah.presentation.navigation.WarningsScreenRout
import com.example.shekinah.presentation.navigation.createAcountScreen
import com.example.shekinah.presentation.navigation.listScreen
import com.example.shekinah.presentation.navigation.loginScreen
import com.example.shekinah.presentation.navigation.placeorderscren
import com.example.shekinah.presentation.navigation.prayDetailsScreen
import com.example.shekinah.presentation.navigation.profileScreen
import com.example.shekinah.presentation.navigation.recoverPasswordScreen
import com.example.shekinah.presentation.navigation.warningsScreen
import com.example.shekinah.presentation.screen.login.LoginRoute


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            ShekinahNavHost(navController = navController)

        }
    }
}