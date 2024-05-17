package com.sopt.now.presentation.auth.signup

sealed class SignUpState {
    data object LOADING : SignUpState()
    data object SUCCESS : SignUpState()
    data class ERROR(val errorMessage: Int) : SignUpState()
}
