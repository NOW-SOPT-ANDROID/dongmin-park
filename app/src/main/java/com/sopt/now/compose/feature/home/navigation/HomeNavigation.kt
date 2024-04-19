package com.sopt.now.compose.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.home.HomeRoute

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(HomeRoute.ROUTE, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues
) {
    composable(route = HomeRoute.ROUTE) {
        HomeRoute()
    }
}

object HomeRoute {
    const val ROUTE = "home"
}
