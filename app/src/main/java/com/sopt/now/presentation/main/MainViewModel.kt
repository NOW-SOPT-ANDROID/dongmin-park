package com.sopt.now.presentation.main

import androidx.lifecycle.ViewModel
import com.sopt.now.data.local.mockProfileList
import com.sopt.now.model.User

class MainViewModel : ViewModel() {
    lateinit var userInfo: User

    fun setUserInfoValue(user: User) {
        userInfo = user
    }

    fun getMockProfileList() = mockProfileList
}
