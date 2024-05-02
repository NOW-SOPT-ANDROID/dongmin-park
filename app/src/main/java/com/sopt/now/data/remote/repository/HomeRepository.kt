package com.sopt.now.data.remote.repository

import com.sopt.now.data.dto.response.ResponseUserInfo
import com.sopt.now.data.remote.service.HomeService
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeService: HomeService,
) {
    suspend fun getUserInfo(): Result<ResponseUserInfo?> =
        runCatching {
            homeService.getUserInfo().data
        }
}
