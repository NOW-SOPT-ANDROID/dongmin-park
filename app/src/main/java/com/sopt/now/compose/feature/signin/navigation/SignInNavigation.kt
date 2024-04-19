package com.sopt.now.compose.feature.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.signin.SignInRoute

fun NavController.navigateSignIn() {
    navigate(SignInRoute.ROUTE)
}

fun NavGraphBuilder.signInNavGraph(
    onSignUpClick: () -> Unit,
    onMainClick: () -> Unit,
    onShowErrorSnackBar: (Int) -> Unit
) {
    composable(route = SignInRoute.ROUTE) {
        SignInRoute(
            onSignUpClick = onSignUpClick,
            onMainClick = onMainClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}

object SignInRoute {
    const val ROUTE = "signin"
}
