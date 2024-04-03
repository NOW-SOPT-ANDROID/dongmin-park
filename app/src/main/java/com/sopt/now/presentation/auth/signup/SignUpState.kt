package com.sopt.now.presentation.auth.signup

import com.sopt.now.R

sealed class SignUpState {
    data object SUCCESS : SignUpState()
    data class ERROR(val errorMessage: Int) : SignUpState()
}
