package com.sopt.now.compose.feature.my

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.local.UserDataStore
import com.sopt.now.compose.domain.repository.HomeRepository
import com.sopt.now.compose.model.User
import com.sopt.now.compose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val homeRepository: HomeRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<MyState> = MutableStateFlow(MyState())
    val state: StateFlow<MyState>
        get() = _state.asStateFlow()

    fun getUserInfo() {
        viewModelScope.launch {
            homeRepository.getUserInfo()
                .onSuccess {
                    setState(it.authenticationId, it.nickname, it.phone)
                }.onFailure {
                    Log.e("TAG", "getUserInfo: ERRRERRRRRRRR", )
                }
        }
    }

    private fun setState(id: String, nickname: String, phone: String) {
        val user = User(id, userDataStore.pw, nickname, phone)

        _state.value = MyState(UiState.Success(user))
    }
}
