package com.sopt.now.compose.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sopt.now.compose.data.remote.datasource.ReqresDataSource
import com.sopt.now.compose.data.remote.dto.response.reqres.toResponseUserList
import com.sopt.now.compose.domain.entity.response.ReqresUserData
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class UserPagingSource @Inject constructor(
    private val requreDataSource: ReqresDataSource,
) : PagingSource<Int, ReqresUserData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReqresUserData> {
        return try {
            val page = params.key ?: 1
            val result = requreDataSource.getUserList(page)

            val data = result.toResponseUserList()
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (data.isNotEmpty()) page + 1 else null

            LoadResult.Page(data, prevKey, nextKey)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ReqresUserData>): Int? {
        return state.anchorPosition
    }
}
