package com.sopt.now.compose.feature.home

import com.sopt.now.compose.model.User
import com.sopt.now.compose.util.UiState

data class HomeState(
    var loadState: UiState<User> = UiState.Loading,
)
