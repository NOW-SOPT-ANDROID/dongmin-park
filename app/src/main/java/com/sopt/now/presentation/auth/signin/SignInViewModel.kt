package com.sopt.now.presentation.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.R
import com.sopt.now.data.dto.request.RequestSignInDto
import com.sopt.now.data.local.UserDataStore
import com.sopt.now.data.remote.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _signInState = MutableStateFlow<SignInState>(SignInState.LOADING)
    val signInState: StateFlow<SignInState> = _signInState

    fun signInBtnClicked(id: String, pw: String) {
        viewModelScope.launch {
            _signInState.value = SignInState.LOADING

            authRepository.postSignIn(
                RequestSignInDto(id, pw)
            ).onSuccess {
                _signInState.value = SignInState.SUCCESS
            }.onFailure {
                _signInState.value = SignInState.ERROR(R.string.network_error)
            }
        }
    }
}
