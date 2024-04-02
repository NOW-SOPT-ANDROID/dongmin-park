package com.sopt.now.compose.feature.signin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewModel : ViewModel() {
    private val _state: MutableStateFlow<SignInState> = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SignInSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SignInSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun fetchId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun fetchPw(pw: String) {
        _state.value = _state.value.copy(pw = pw)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun signInBtnClicked() {
        when {
            _state.value.id.length !in 6..10 -> _sideEffect.emit(SignInSideEffect.SnackBar("ID ERROR"))
            _state.value.pw.length !in 8..12 -> _sideEffect.emit(SignInSideEffect.SnackBar("PW ERROR"))
            else -> _sideEffect.emit(SignInSideEffect.NavigateToMain)
        }
        _sideEffect.resetReplayCache()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun signUpBtnClicked() {
        _sideEffect.emit(SignInSideEffect.NavigateToSignUp)
        _sideEffect.resetReplayCache()
    }
}