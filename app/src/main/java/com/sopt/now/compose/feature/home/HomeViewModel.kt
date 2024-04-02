package com.sopt.now.compose.feature.home

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.User
import com.sopt.now.compose.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    fun setState(uiState: UiState<User>) {
        _state.value.loadState = uiState
    }
}
