package com.sopt.now.compose.feature.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.home.HomeScreen
import com.sopt.now.compose.feature.signin.SignInScreen
import com.sopt.now.compose.feature.signup.SignUpScreen

@Composable
fun MainScreen(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screen.SignIn.route) {
        composable(
            route = Screen.SignIn.route
        ) {
            SignInScreen(navController)
        }

        composable(
            route = Screen.SignUp.route
        ) {
            SignUpScreen(navController = navController)
        }

        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }
    }
}
