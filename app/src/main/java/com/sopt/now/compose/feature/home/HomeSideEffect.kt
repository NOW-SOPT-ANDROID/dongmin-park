package com.sopt.now.compose.feature.home

sealed class HomeSideEffect {
    data class SnackBar(val message: String) : HomeSideEffect()
}
