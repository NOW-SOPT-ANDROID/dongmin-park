package com.sopt.now.compose.feature.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.search.SearchRoute

fun NavController.navigateSearch(navOptions: NavOptions) {
    navigate(SearchRoute.ROUTE, navOptions)
}

fun NavGraphBuilder.searchNavGraph(paddingValue: PaddingValues) {
    composable(route = SearchRoute.ROUTE) {
        SearchRoute(paddingValue = paddingValue)
    }
}

object SearchRoute {
    const val ROUTE = "search"
}
