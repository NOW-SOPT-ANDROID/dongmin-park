package com.sopt.now.compose.data.remote.repositoryImpl

import com.sopt.now.compose.data.remote.dto.response.toResponseUserInfo
import com.sopt.now.compose.data.remote.service.HomeService
import com.sopt.now.compose.domain.entity.response.ResponseUserInfo
import com.sopt.now.compose.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService,
) : HomeRepository {
    override suspend fun getUserInfo(): Result<ResponseUserInfo> =
        runCatching {
            homeService.getUserInfo().data.toResponseUserInfo()
        }
}
