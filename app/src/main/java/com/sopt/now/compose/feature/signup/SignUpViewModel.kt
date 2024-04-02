package com.sopt.now.compose.feature.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SignUpSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SignUpSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun fetchId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun fetchPw(pw: String) {
        _state.value = _state.value.copy(pw = pw)
    }

    fun fetchNickname(nickname: String) {
        _state.value = _state.value.copy(nickname = nickname)
    }

    fun fetchJuryang(juryang: String) {
        _state.value = _state.value.copy(juryang = juryang)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun signUpBtnClicked() {
        when {
            _state.value.id.length !in 6..10 -> _sideEffect.emit(SignUpSideEffect.SnackBar("ID ERROR"))
            _state.value.pw.length !in 8..12 -> _sideEffect.emit(SignUpSideEffect.SnackBar("PW ERROR"))
            _state.value.nickname.isBlank() -> _sideEffect.emit(SignUpSideEffect.SnackBar("NICKNAME ERROR"))
            _state.value.juryang.isBlank() -> _sideEffect.emit(SignUpSideEffect.SnackBar("JURYANG ERROR"))
            else -> _sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
        }
        _sideEffect.resetReplayCache()
    }
}