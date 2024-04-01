package com.sopt.now.presentation.auth.signup

sealed class SignUpState(val errorMessage: String) {
    data object SUCCESS : SignUpState("SUCCESS")
    data object IdState : SignUpState("ID ERROR")
    data object PwState : SignUpState("PW ERROR")
    data object NicknameState : SignUpState("NICKNAME ERROR")
    data object JuryangState : SignUpState("JURYANG ERROR")
}
