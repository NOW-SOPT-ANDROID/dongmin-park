package com.sopt.now.compose.feature.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.now.compose.feature.home.HomeScreen
import com.sopt.now.compose.feature.signin.SignInScreen
import com.sopt.now.compose.feature.signup.SignUpScreen

@Composable
fun MainScreen(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Graph.Auth.route) {
        authGraph(navController)
        mainGraph(navController)
    }
}

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(startDestination = Screen.SignIn.route, route = Graph.Auth.route) {
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
    }
}

fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(startDestination = Screen.Home.route, route = Graph.Main.route) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }
    }
}
