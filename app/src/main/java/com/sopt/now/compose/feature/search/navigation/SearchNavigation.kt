package com.sopt.now.compose.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.search.SearchScreen

fun NavController.navigateSearch(navOptions: NavOptions) {
    navigate(SearchRoute.ROUTE, navOptions)
}

fun NavGraphBuilder.searchNavGraph() {
    composable(route = SearchRoute.ROUTE) {
        SearchScreen()
    }
}

object SearchRoute {
    const val ROUTE = "search"
}
