package com.sopt.now.compose.feature.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.signup.SignUpRoute
import com.sopt.now.compose.model.User

fun NavController.navigateSignUp() {
    navigate(SignUpRoute.ROUTE)
}

fun NavGraphBuilder.signUpNavGraph(onSignInClick: (User) -> Unit) {
    composable(route = SignUpRoute.ROUTE) {
        SignUpRoute(
            onSignInClick = onSignInClick
        )
    }
}

object SignUpRoute {
    const val ROUTE = "signUp"
}
