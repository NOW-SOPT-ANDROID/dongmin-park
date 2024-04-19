package com.sopt.now.compose.feature.my.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.my.MyRoute

fun NavController.navigateMy(navOptions: NavOptions) {
    navigate(MyRoute.ROUTE, navOptions)
}

fun NavGraphBuilder.myNavGraph(paddingValue: PaddingValues) {
    composable(route = MyRoute.ROUTE) {
        MyRoute(paddingValue)
    }
}

object MyRoute {
    const val ROUTE = "my"
}
