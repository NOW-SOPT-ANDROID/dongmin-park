package com.sopt.now.compose.data.remote.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sopt.now.compose.data.remote.datasource.ReqresDataSource
import com.sopt.now.compose.data.remote.paging.UserPagingSource
import com.sopt.now.compose.domain.entity.response.ReqresUserModel
import com.sopt.now.compose.domain.repository.ReqresRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReqresRepositoryImpl @Inject constructor(
    private val reqresDataSource: ReqresDataSource
) : ReqresRepository {
    override fun getUserList(): Flow<PagingData<ReqresUserModel>> =
        Pager(
            config = PagingConfig(pageSize = 1)) {
            UserPagingSource(reqresDataSource)
        }.flow
}
