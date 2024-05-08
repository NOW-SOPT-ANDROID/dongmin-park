package com.sopt.now.presentation.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.R
import com.sopt.now.data.local.UserDataStore
import com.sopt.now.data.remote.dto.request.RequestSignInDto
import com.sopt.now.data.remote.repository.AuthRepository
import com.sopt.now.model.User
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
    var user: User? = null

    fun setUser(userInfo: User) {
        user = userInfo
    }

    fun signInBtnClicked(id: String, pw: String) {
        viewModelScope.launch {
            _signInState.value = SignInState.LOADING

            val header = authRepository.postSignIn(RequestSignInDto(id, pw))

            if (header == null) {
                _signInState.value = SignInState.ERROR(R.string.network_error)
            } else {
                setUserData(header)
                _signInState.value = SignInState.SUCCESS
            }
        }
    }

    private fun setUserData(memberId: String) {
        with(userDataStore) {
            userId = memberId
        }
    }
}
