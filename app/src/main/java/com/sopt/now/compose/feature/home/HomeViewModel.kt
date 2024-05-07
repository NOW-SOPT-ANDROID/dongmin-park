package com.sopt.now.compose.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sopt.now.compose.domain.entity.response.ReqresUserModel
import com.sopt.now.compose.domain.repository.ReqresRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val reqresRepository: ReqresRepository,
) : ViewModel() {
    val userListStream: Flow<PagingData<ReqresUserModel>> = flow {
        getReqresUserData().collect(
            this
        )
    }

    private suspend fun getReqresUserData(): Flow<PagingData<ReqresUserModel>> {
        return reqresRepository.getUserList()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
    }
}
