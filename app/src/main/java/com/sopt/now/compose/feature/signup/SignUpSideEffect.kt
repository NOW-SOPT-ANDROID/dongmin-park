package com.sopt.now.compose.feature.signup

sealed class SignUpSideEffect {
    data object NavigateToSignIn: SignUpSideEffect()
    data class SnackBar(val message: String): SignUpSideEffect()
}