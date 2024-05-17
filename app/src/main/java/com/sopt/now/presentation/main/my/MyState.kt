package com.sopt.now.presentation.main.my

import com.sopt.now.model.User

sealed class MyState {
    data object LOADING : MyState()
    data class SUCCESS(val user: User) : MyState()
    data class ERROR(val errorMessage: Int) : MyState()
}
