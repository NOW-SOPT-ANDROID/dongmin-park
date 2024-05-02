package com.sopt.now.presentation.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.R
import com.sopt.now.data.local.mockProfileList
import com.sopt.now.data.remote.repository.HomeRepository
import com.sopt.now.model.User
import com.sopt.now.presentation.auth.signup.SignUpActivity.Companion.USER_KEY
import com.sopt.now.presentation.main.my.MyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val homeRepository: HomeRepository,
) : ViewModel() {
    private val _myState = MutableStateFlow<MyState>(MyState.LOADING)
    val myState: StateFlow<MyState> = _myState

    private var userInfo: User?
        get() = savedStateHandle.get<User>(USER_KEY)
        set(value) = savedStateHandle.set(USER_KEY, value)

    fun getMockProfileList() = mockProfileList

    fun getProfileInfo() {
        viewModelScope.launch {
            _myState.value = MyState.LOADING
            homeRepository.getUserInfo()
                .onSuccess {
                    _myState.value = MyState.SUCCESS(
                        User(
                            id = it.authenticationId,
                            pw = userInfo?.pw.orEmpty(),
                            nickname = it.nickname,
                            phoneNumber = it.phone,
                        )
                    )
                }.onFailure {
                    _myState.value = MyState.ERROR(R.string.network_error)
                }
        }
    }
}
