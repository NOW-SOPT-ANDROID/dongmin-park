package com.sopt.now.compose.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.R
import com.sopt.now.compose.data.remote.repositoryImpl.AuthRepositoryImpl
import com.sopt.now.compose.domain.entity.request.RequestUserEntity
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
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SignUpSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SignUpSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun fetchId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun fetchPw(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun fetchNickname(nickname: String) {
        _state.value = _state.value.copy(nickname = nickname)
    }

    fun fetchPhoneNumber(phone: String) {
        _state.value = _state.value.copy(phoneNumber = phone)
    }

    fun signUpBtnClicked() {
        viewModelScope.launch {
            authRepository.registerUser(
                state.value.run {
                    RequestUserEntity(
                        id,
                        password,
                        nickname,
                        phoneNumber
                    )
                }
            ).onSuccess {
                val header = it.headers()[AuthRepositoryImpl.HEADER].orEmpty()

                setUserData(header)
                _sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
            }.onFailure {
                emitErrorMessage()
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun emitErrorMessage() {
        val errorMessage = when {
            _state.value.id.length !in ID_MIN_LEN..ID_MAX_LEN -> R.string.id_error

            _state.value.password.length !in PW_MIN_LEN..PW_MAX_LEN -> R.string.pw_error

            _state.value.nickname.isBlank() -> R.string.nickname_error
            _state.value.phoneNumber.isBlank() -> R.string.phone_error
            else -> R.string.server_error
        }

        _sideEffect.emit(SignUpSideEffect.SnackBar(errorMessage))
        _sideEffect.resetReplayCache()
    }

    private fun setUserData(memberId: String) {
        authRepository.setUserId(memberId)
        authRepository.setId(state.value.id)
        authRepository.setPassword(state.value.password)
        authRepository.setNickname(state.value.nickname)
        authRepository.setPhoneNumber(state.value.phoneNumber)
    }

    companion object {
        const val ID_MIN_LEN = 6
        const val ID_MAX_LEN = 10
        const val PW_MIN_LEN = 8
        const val PW_MAX_LEN = 12
    }
}
