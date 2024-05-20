package com.sopt.now.compose.data.remote.service

import com.sopt.now.compose.data.remote.dto.response.BaseResponseDto
import com.sopt.now.compose.data.remote.dto.response.ResponseUserDto
import retrofit2.http.GET

interface HomeService {
    @GET("member/info")
    suspend fun getUserInfo(): BaseResponseDto<ResponseUserDto>
}
