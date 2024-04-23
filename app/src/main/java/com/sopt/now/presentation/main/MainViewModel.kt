package com.sopt.now.presentation.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sopt.now.data.local.mockProfileList
import com.sopt.now.model.User

class MainViewModel(val savedStateHandle: SavedStateHandle) : ViewModel() {
    var userInfo: User?
        get() = savedStateHandle.get<User>(USER)
        set(value) = savedStateHandle.set(USER, value)

    fun setUserInfoValue(user: User) {
        userInfo = user
    }

    fun getMockProfileList() = mockProfileList

    companion object {
        val USER = "user"
    }
}
