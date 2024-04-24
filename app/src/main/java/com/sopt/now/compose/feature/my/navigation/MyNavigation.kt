package com.sopt.now.compose.feature.my.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.my.MyRoute

fun NavController.navigateMy(navOptions: NavOptions) {
    navigate(MyRoute.ROUTE, navOptions)
}

fun NavGraphBuilder.myNavGraph(
    modifier: Modifier
) {
    composable(route = MyRoute.ROUTE) {
        MyRoute(modifier = modifier)
    }
}

object MyRoute {
    const val ROUTE = "my"
}
