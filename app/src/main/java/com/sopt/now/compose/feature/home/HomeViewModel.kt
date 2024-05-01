package com.sopt.now.compose.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.domain.repository.ReqresRepository
import com.sopt.now.compose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val reqresRepository: ReqresRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    fun getUserList() {
        viewModelScope.launch {
            reqresRepository.getUserList()
                .onSuccess {
                    _state.value = _state.value.copy(
                        loadState = UiState.Success(it)
                    )
                }.onFailure {
                    _state.value = _state.value.copy(
                        loadState = UiState.Failure
                    )
                }
        }
    }
}
