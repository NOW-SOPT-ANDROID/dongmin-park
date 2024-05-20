package com.sopt.now.compose.data.remote.repositoryImpl

import com.sopt.now.compose.data.remote.datasource.HomeDataSource
import com.sopt.now.compose.data.remote.dto.response.toResponseUserInfo
import com.sopt.now.compose.domain.entity.response.ResponseUserInfo
import com.sopt.now.compose.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getUserInfo(): Result<ResponseUserInfo> =
        runCatching {
            homeDataSource.getUserInfo().data.toResponseUserInfo()
        }
}
