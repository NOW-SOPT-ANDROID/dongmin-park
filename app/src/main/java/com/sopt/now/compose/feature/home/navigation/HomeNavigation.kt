package com.sopt.now.compose.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.home.HomeRoute

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(HomeRoute.ROUTE, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    modifier: Modifier
) {
    composable(route = HomeRoute.ROUTE) {
        HomeRoute(modifier = modifier)
    }
}

object HomeRoute {
    const val ROUTE = "home"
}
