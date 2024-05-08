package com.sopt.now.compose.feature.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.R
import com.sopt.now.compose.data.remote.repositoryImpl.AuthRepositoryImpl
import com.sopt.now.compose.domain.entity.request.RequestSignInEntity
import com.sopt.now.compose.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<SignInState> = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SignInSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SignInSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun setInfo() {
        _state.value = _state.value.copy(
            id = authRepository.getId(),
            password = authRepository.getPassword(),
            nickname = authRepository.getNickname()
        )
    }

    fun fetchId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun fetchPw(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    suspend fun signInBtnClicked() {
        viewModelScope.launch {
            authRepository.postSignIn(
                RequestSignInEntity(
                    _state.value.id,
                    _state.value.password
                )
            ).onSuccess {
                val header = it.headers()[AuthRepositoryImpl.HEADER].orEmpty()

                setUserData(header)
                _sideEffect.emit(SignInSideEffect.NavigateToMain)
            }.onFailure {
                _sideEffect.emit(
                    SignInSideEffect.SnackBar(
                        R.string.server_error
                    )
                )
            }
        }
    }

    private fun setUserData(memberId: String) {
        authRepository.setUserId(memberId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun signUpBtnClicked() {
        _sideEffect.emit(SignInSideEffect.NavigateToSignUp)
        _sideEffect.resetReplayCache()
    }
}
