package com.sopt.now.compose.feature.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.R
import com.sopt.now.compose.domain.repository.HomeRepository
import com.sopt.now.compose.model.User
import com.sopt.now.compose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<MyState> = MutableStateFlow(MyState())
    val state: StateFlow<MyState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<MySideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<MySideEffect>
        get() = _sideEffect.asSharedFlow()

    fun getUserInfo() {
        viewModelScope.launch {
            homeRepository.getUserInfo()
                .onSuccess {
                    setState(it.authenticationId, it.nickname, it.phone)
                }.onFailure {
                    _sideEffect.emit(MySideEffect.SnackBar(R.string.server_error))
                }
        }
    }

    private fun setState(id: String, nickname: String, phone: String) {
        val user = User(id = id, nickname = nickname, phoneNumber = phone)

        _state.value = MyState(UiState.Success(user))
    }
}
