package com.sopt.now.compose.feature.main

sealed class Screen(val route: String) {
    data object SignIn : Screen(route = "signin")
    data object SignUp : Screen(route = "signup")
    data object Home : Screen(route = "home")
}
