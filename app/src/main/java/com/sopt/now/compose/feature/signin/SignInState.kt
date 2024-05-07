package com.sopt.now.compose.feature.signin

data class SignInState(
    val id: String = "",
    val password: String = "",
    val nickname: String = "",
    val phoneNumber: String = ""
)
