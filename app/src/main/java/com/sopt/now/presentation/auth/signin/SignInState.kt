package com.sopt.now.presentation.auth.signin

sealed class SignInState {
    data object LOADING : SignInState()
    data object SUCCESS : SignInState()
    data class ERROR(val errorMessage: Int) : SignInState()
}
