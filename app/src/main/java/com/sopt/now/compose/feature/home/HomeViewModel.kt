package com.sopt.now.compose.feature.home

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.R
import com.sopt.now.compose.data.local.DataStore
import com.sopt.now.compose.model.Friend
import com.sopt.now.compose.model.User
import com.sopt.now.compose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStore: DataStore
) : ViewModel() {
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    fun setState() {
        val user = dataStore.run {
            User(id, pw, nickname, juryang)
        }

        _state.value = HomeState(UiState.Success(user))
    }

    val friendDataList = listOf(
        Friend(
            R.drawable.img_error,
            "박동민",
            "지금은 금요일 저녁 10시"
        ),
        Friend(
            R.drawable.img_error,
            "동민",
            "벼락치기 힘들다"
        ),
        Friend(
            R.drawable.img_error,
            "민",
            "다음주엔 미리해야지"
        ),
        Friend(
            R.drawable.img_error,
            "송혜음",
            "히히 안드 재밌다"
        ),
        Friend(
            R.drawable.img_error,
            "이석준",
            "솔직히 내가 리드실력임 ㅋㅋ"
        ),
        Friend(
            R.drawable.img_error,
            "박유진",
            "으앙 어려워요"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
        Friend(
            R.drawable.img_error,
            "더미",
            "더미더미"
        ),
    )
}
