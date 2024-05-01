package com.sopt.now.compose.feature.my

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.local.UserDataStore
import com.sopt.now.compose.model.User
import com.sopt.now.compose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val userDataStore: UserDataStore
) : ViewModel() {
    private val _state: MutableStateFlow<MyState> = MutableStateFlow(MyState())
    val state: StateFlow<MyState>
        get() = _state.asStateFlow()

    fun setState() {
        val user = userDataStore.run {
            User(id, pw, nickname, phoneNumber)
        }

        _state.value = MyState(UiState.Success(user))
    }
}
