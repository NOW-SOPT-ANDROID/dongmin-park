package com.sopt.now.compose.feature.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.signup.SignUpRoute

fun NavController.navigateSignUp() {
    navigate(SignUpRoute.ROUTE)
}

fun NavGraphBuilder.signUpNavGraph(
    onSignInClick: () -> Unit,
    onShowErrorSnackBar: (Int) -> Unit,
) {
    composable(route = SignUpRoute.ROUTE) {
        SignUpRoute(
            onSignInClick = onSignInClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}

object SignUpRoute {
    const val ROUTE = "signUp"
}
