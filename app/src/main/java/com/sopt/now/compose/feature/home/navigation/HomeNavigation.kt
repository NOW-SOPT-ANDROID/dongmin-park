package com.sopt.now.compose.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.home.HomeScreen
import com.sopt.now.compose.model.User

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(HomeRoute.ROUTE, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    user: User?,
    paddingValues: PaddingValues
) {
    composable(route = HomeRoute.ROUTE) {
        HomeScreen(user)
    }
}

object HomeRoute {
    const val ROUTE = "home"
}
