package com.sopt.now.compose.feature.my

sealed class MySideEffect {
    data class SnackBar(val message: Int) : MySideEffect()
}
