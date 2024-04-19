package com.sopt.now.compose.feature.my.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.my.MyScreen

fun NavController.navigateMy(navOptions: NavOptions) {
    navigate(MyRoute.ROUTE, navOptions)
}

fun NavGraphBuilder.myNavGraph() {
    composable(route = MyRoute.ROUTE) {
        MyScreen()
    }
}

object MyRoute {
    const val ROUTE = "my"
}
