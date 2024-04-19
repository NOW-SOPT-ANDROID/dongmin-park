package com.sopt.now.compose.feature.signin.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.signin.SignInRoute
import com.sopt.now.compose.model.User

fun NavController.navigateSignIn() {
    navigate(SignInRoute.ROUTE)
}

fun NavGraphBuilder.signInNavGraph(
    onSignUpClick: () -> Unit,
    onMainClick: () -> Unit
) {
    composable(route = SignInRoute.ROUTE) {
        SignInRoute(
            onSignUpClick = onSignUpClick,
            onMainClick = onMainClick
        )
    }
}

object SignInRoute {
    const val ROUTE = "signin"
}
