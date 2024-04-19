package com.sopt.now.compose.feature.signin

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.R
import com.sopt.now.compose.data.local.DataStore
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
class SignInViewModel @Inject constructor(
    private val dataStore: DataStore
) : ViewModel() {
    private val _state: MutableStateFlow<SignInState> = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState>
        get() = _state.asStateFlow()

    private val _previousState: MutableStateFlow<SignInState> = MutableStateFlow(SignInState())

    private val _sideEffect: MutableSharedFlow<SignInSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SignInSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun setInfo() {
        _state.value = _state.value.copy(
            id = dataStore.id,
            pw = dataStore.pw,
            nickname = dataStore.nickname,
            juryang = dataStore.juryang
        )

        _previousState.value = _previousState.value.copy(
            id = dataStore.id,
            pw = dataStore.pw,
            nickname = dataStore.nickname,
            juryang = dataStore.juryang
        )
    }

    fun fetchId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun fetchPw(pw: String) {
        _state.value = _state.value.copy(pw = pw)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun signInBtnClicked() {
        when {
            _state.value.id == "" -> _sideEffect.emit(
                SignInSideEffect.SnackBar(
                    R.string.id_error
                )
            )

            _state.value.pw == "" -> _sideEffect.emit(
                SignInSideEffect.SnackBar(
                    R.string.pw_error
                )
            )

            _state.value.id != _previousState.value.id -> _sideEffect.emit(
                SignInSideEffect.SnackBar(
                    R.string.id_error
                )
            )

            _state.value.pw != _previousState.value.pw -> _sideEffect.emit(
                SignInSideEffect.SnackBar(
                    R.string.pw_error
                )
            )

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
