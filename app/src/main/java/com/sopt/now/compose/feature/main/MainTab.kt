package com.sopt.now.compose.feature.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.sopt.now.compose.feature.home.navigation.HomeRoute
import com.sopt.now.compose.feature.my.navigation.MyRoute
import com.sopt.now.compose.feature.search.navigation.SearchRoute

enum class MainTab(
    val iconResId: ImageVector,
    internal val contentDescription: String,
    val route: String,
) {
    HOME(
        iconResId = Icons.Default.Home,
        contentDescription = "홈",
        HomeRoute.ROUTE,
    ),
    SEARCH(
        iconResId = Icons.Default.Search,
        contentDescription = "검색",
        SearchRoute.ROUTE,
    ),
    MY(
        iconResId = Icons.Default.Person,
        contentDescription = "마이페이지",
        MyRoute.ROUTE,
    );

    companion object {
        operator fun contains(route: String): Boolean {
            return entries.map { it.route }.contains(route)
        }

        fun find(route: String): MainTab? {
            return entries.find { it.route == route }
        }
    }
}
