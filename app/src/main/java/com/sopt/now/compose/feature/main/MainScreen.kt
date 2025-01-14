package com.sopt.now.compose.feature.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.sopt.now.compose.ext.noRippleClickable
import com.sopt.now.compose.feature.home.navigation.homeNavGraph
import com.sopt.now.compose.feature.my.navigation.myNavGraph
import com.sopt.now.compose.feature.search.navigation.searchNavGraph
import com.sopt.now.compose.feature.signin.navigation.SignInRoute
import com.sopt.now.compose.feature.signin.navigation.signInNavGraph
import com.sopt.now.compose.feature.signup.navigation.signUpNavGraph
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val localContextResource = LocalContext.current.resources
    val onShowErrorSnackBar: (Int) -> Unit = { errorMessage ->
        coroutineScope.launch {
            snackBarHostState.currentSnackbarData?.dismiss()
            snackBarHostState.showSnackbar(localContextResource.getString(errorMessage))
        }
    }

    Scaffold(
        content = { paddingValue ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceDim)
            ) {
                NavHost(
                    navController = navigator.navController,
                    startDestination = navigator.startDestination,
                ) {
                    signInNavGraph(
                        onSignUpClick = { navigator.navigateSignUp() },
                        onMainClick = {
                            navigator.navController.navigate(MainTab.HOME.route) {
                                popUpTo(SignInRoute.ROUTE) {
                                    inclusive = true
                                }
                            }
                        },
                        onShowErrorSnackBar = onShowErrorSnackBar
                    )
                    signUpNavGraph(
                        onSignInClick = { navigator.navigateSignIn() },
                        onShowErrorSnackBar = onShowErrorSnackBar
                    )
                    homeNavGraph(Modifier.padding(paddingValue))
                    searchNavGraph(Modifier.padding(paddingValue))
                    myNavGraph(
                        Modifier.padding(paddingValue),
                        onShowErrorSnackBar = onShowErrorSnackBar
                    )
                }
            }
        },
        bottomBar = {
            MainBottomBar(
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries.toImmutableList(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    )
}

@Composable
private fun MainBottomBar(
    visible: Boolean,
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideIn { IntOffset(0, it.height) },
        exit = fadeOut() + slideOut { IntOffset(0, it.height) }
    ) {
        Row(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(start = 8.dp, end = 8.dp, bottom = 28.dp)
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(size = 28.dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(horizontal = 28.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            tabs.forEach { tab ->
                MainBottomBarItem(
                    tab = tab,
                    selected = tab == currentTab,
                    onClick = { onTabSelected(tab) },
                )
            }
        }
    }
}

@Composable
private fun RowScope.MainBottomBarItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .noRippleClickable(onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = tab.iconImageVector,
            contentDescription = tab.contentDescription,
            tint = if (selected) {
                MaterialTheme.colorScheme.error
            } else {
                MaterialTheme.colorScheme.outline
            },
            modifier = Modifier.size(34.dp),
        )
    }
}
