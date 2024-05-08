package com.sopt.now.compose.data.remote.datasourceimpl

import com.sopt.now.compose.data.remote.datasource.HomeDataSource
import com.sopt.now.compose.data.remote.dto.response.BaseResponseDto
import com.sopt.now.compose.data.remote.dto.response.ResponseUserDto
import com.sopt.now.compose.data.remote.service.HomeService
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeService: HomeService,
) : HomeDataSource {
    override suspend fun getUserInfo(): BaseResponseDto<ResponseUserDto> = homeService.getUserInfo()
}
