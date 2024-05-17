package com.sopt.now.presentation.main.home

import com.sopt.now.model.Profile

sealed class HomeState {
    data object LOADING : HomeState()
    data class SUCCESS(val profileList: List<Profile>) : HomeState()
    data class ERROR(val errorMessage: Int) : HomeState()
}
