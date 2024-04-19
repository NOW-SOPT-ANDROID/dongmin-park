package com.sopt.now.compose.feature.signin.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.signin.SignInRoute
import com.sopt.now.compose.model.User

fun NavController.navigateSignIn() {
    navigate(SignInScreen.ROUTE)
}

fun NavGraphBuilder.signInNavGraph(
    onSignUpClick: () -> Unit,
    onMainClick: () -> Unit
) {
    composable(route = SignInScreen.ROUTE) {
        SignInRoute(
            onSignUpClick = onSignUpClick,
            onMainClick = onMainClick
        )
    }
}

object SignInScreen {
    const val ROUTE = "signin"
}
