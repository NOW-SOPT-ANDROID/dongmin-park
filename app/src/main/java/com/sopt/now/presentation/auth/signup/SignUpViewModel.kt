package com.sopt.now.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.R
import com.sopt.now.data.local.UserDataStore
import com.sopt.now.data.remote.dto.request.RequestSignUpDto
import com.sopt.now.data.remote.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.LOADING)
    val signUpState: StateFlow<SignUpState> = _signUpState

    fun signUpBtnClicked(id: String, pw: String, nickname: String, phoneNumber: String) {
        viewModelScope.launch {
            _signUpState.value = SignUpState.LOADING

            val user = RequestSignUpDto(
                id,
                pw,
                nickname,
                phoneNumber
            )
            val header = authRepository.postSignUp(user)

            if (header == null) {
                _signUpState.value = SignUpState.ERROR(R.string.network_error)
            } else {
                setUserData(user, header)
                _signUpState.value = SignUpState.SUCCESS
            }
        }
    }

    private fun setUserData(user: RequestSignUpDto, memberId: String) {
        with(userDataStore) {
            userId = memberId
            id = user.authenticationId
            pw = user.password
            nickname = user.nickname
            phoneNumber = user.phone
        }
    }
}
