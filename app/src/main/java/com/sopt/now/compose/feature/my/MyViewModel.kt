package com.sopt.now.compose.feature.my

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.local.DataStore
import com.sopt.now.compose.model.User
import com.sopt.now.compose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val dataStore: DataStore
) : ViewModel() {
    private val _state: MutableStateFlow<MyState> = MutableStateFlow(MyState())
    val state: StateFlow<MyState>
        get() = _state.asStateFlow()

    fun setState() {
        val user = dataStore.run {
            User(id, pw, nickname, juryang)
        }

        _state.value = MyState(UiState.Success(user))
    }
}
