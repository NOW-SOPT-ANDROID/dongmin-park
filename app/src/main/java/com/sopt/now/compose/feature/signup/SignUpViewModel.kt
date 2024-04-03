package com.sopt.now.compose.feature.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.R
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
            _state.value.id.length !in ID_MIN_LEN..ID_MAX_LEN -> _sideEffect.emit(SignUpSideEffect.SnackBar(R.string.id_error))
            _state.value.pw.length !in PW_MIN_LEN..PW_MAX_LEN -> _sideEffect.emit(SignUpSideEffect.SnackBar(R.string.pw_error))
            _state.value.nickname.isBlank() -> _sideEffect.emit(SignUpSideEffect.SnackBar(R.string.nickname_error))
            _state.value.juryang.isBlank() -> _sideEffect.emit(SignUpSideEffect.SnackBar(R.string.juryang_error))
            else -> _sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
        }
        _sideEffect.resetReplayCache()
    }

    companion object {
        const val ID_MIN_LEN = 6
        const val ID_MAX_LEN = 10
        const val PW_MIN_LEN = 12
        const val PW_MAX_LEN = 12
    }
}
