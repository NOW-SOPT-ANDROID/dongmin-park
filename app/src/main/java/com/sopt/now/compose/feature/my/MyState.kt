package com.sopt.now.compose.feature.my

import com.sopt.now.compose.model.User
import com.sopt.now.compose.util.UiState

class MyState(
    var loadState: UiState<User> = UiState.Loading,
)
