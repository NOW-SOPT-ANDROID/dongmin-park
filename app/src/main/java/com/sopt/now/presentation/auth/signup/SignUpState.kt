package com.sopt.now.presentation.auth.signup

import com.sopt.now.R

sealed class SignUpState(val errorMessage: Int) {
    data object SUCCESS : SignUpState(R.string.network_error)
    data object IdState : SignUpState(R.string.id_error)
    data object PwState : SignUpState(R.string.pw_error)
    data object NicknameState : SignUpState(R.string.nickname_error)
    data object JuryangState : SignUpState(R.string.juryang_error)
}
