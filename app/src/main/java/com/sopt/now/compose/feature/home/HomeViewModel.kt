package com.sopt.now.compose.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.sopt.now.compose.domain.entity.response.ResponseUserList
import com.sopt.now.compose.domain.repository.ReqresRepository
import com.sopt.now.compose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val reqresRepository: ReqresRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    val userListPager = Pager(PagingConfig(pageSize = 1)) {
        UserPagingSource(reqresRepository) { uiState ->
            _state.value = _state.value.copy(
                loadState = uiState
            )
        }
    }.flow.cachedIn(viewModelScope)
}

class UserPagingSource(
    private val reqresRepository: ReqresRepository,
    private val setState: (UiState<Unit>) -> Unit,
) : PagingSource<Int, ResponseUserList.UserData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseUserList.UserData> {
        val page = params.key ?: 1
        val result = reqresRepository.getUserList(page)

        return result.fold(
            onSuccess = { response ->
                val data = response.userList
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (data.isNotEmpty()) page + 1 else null

                setState(UiState.Success(Unit))
                LoadResult.Page(data, prevKey, nextKey)
            },
            onFailure = {
                setState(UiState.Failure)

                LoadResult.Error(it)
            }
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ResponseUserList.UserData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}