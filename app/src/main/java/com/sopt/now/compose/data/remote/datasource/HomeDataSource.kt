package com.sopt.now.compose.data.remote.datasource

import com.sopt.now.compose.data.remote.dto.response.BaseResponseDto
import com.sopt.now.compose.data.remote.dto.response.ResponseUserDto

interface HomeDataSource {
    suspend fun getUserInfo(): BaseResponseDto<ResponseUserDto>
}
