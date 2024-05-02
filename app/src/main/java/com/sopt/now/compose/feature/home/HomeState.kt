package com.sopt.now.compose.feature.home

import com.sopt.now.compose.domain.entity.response.ResponseUserList
import com.sopt.now.compose.util.UiState

data class HomeState(
    var loadState: UiState<Unit> = UiState.Loading,
)
