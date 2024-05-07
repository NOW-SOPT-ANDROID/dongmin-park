package com.sopt.now.compose.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sopt.now.compose.domain.entity.response.ReqresUserData
import com.sopt.now.compose.domain.repository.ReqresRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val reqresRepository: ReqresRepository,
) : ViewModel() {
    private val _userState: MutableStateFlow<PagingData<ReqresUserData>> =
        MutableStateFlow(value = PagingData.empty())
    val userState: MutableStateFlow<PagingData<ReqresUserData>>
        get() = _userState

    init {
        viewModelScope.launch {
            getReqresUserData()
        }
    }

    private suspend fun getReqresUserData() {
        reqresRepository.getUserList()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _userState.value = it
            }
    }
}
