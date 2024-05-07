package com.sopt.now.compose.feature.my

import androidx.annotation.StringRes

sealed class MySideEffect {
    data class SnackBar(@StringRes val message: Int) : MySideEffect()
}
