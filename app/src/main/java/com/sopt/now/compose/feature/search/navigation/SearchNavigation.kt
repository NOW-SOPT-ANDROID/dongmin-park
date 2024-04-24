package com.sopt.now.compose.feature.search.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.search.SearchRoute

fun NavController.navigateSearch(navOptions: NavOptions) {
    navigate(SearchRoute.ROUTE, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    modifier: Modifier
) {
    composable(route = SearchRoute.ROUTE) {
        SearchRoute(modifier = modifier)
    }
}

object SearchRoute {
    const val ROUTE = "search"
}
