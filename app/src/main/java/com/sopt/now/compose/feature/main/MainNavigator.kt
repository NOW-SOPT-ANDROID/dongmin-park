package com.sopt.now.compose.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.now.compose.feature.home.navigation.navigateHome
import com.sopt.now.compose.feature.my.navigation.navigateMy
import com.sopt.now.compose.feature.search.navigation.navigateSearch
import com.sopt.now.compose.feature.signin.navigation.SignInRoute
import com.sopt.now.compose.feature.signin.navigation.navigateSignIn
import com.sopt.now.compose.feature.signup.navigation.navigateSignUp

class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = SignInRoute.ROUTE

    val currentTab: MainTab?
        @Composable get() = currentDestination
            ?.route
            ?.let(MainTab::find)

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateHome(navOptions)
            MainTab.SEARCH -> navController.navigateSearch(navOptions)
            MainTab.MY -> navController.navigateMy(navOptions)
        }
    }

    fun navigateSignUp() {
        navController.navigateSignUp()
    }

    fun navigateSignIn() {
        navController.navigateSignIn()
    }

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val currentRoute = currentDestination?.route ?: return false
        return currentRoute in MainTab
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
