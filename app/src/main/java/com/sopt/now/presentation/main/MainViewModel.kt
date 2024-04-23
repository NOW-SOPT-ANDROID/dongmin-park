package com.sopt.now.presentation.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sopt.now.data.local.mockProfileList
import com.sopt.now.model.User
import com.sopt.now.presentation.auth.signup.SignUpActivity.Companion.USER_KEY

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var userInfo: User?
        get() = savedStateHandle.get<User>(USER_KEY)
        set(value) = savedStateHandle.set(USER_KEY, value)

    fun getMockProfileList() = mockProfileList
}
