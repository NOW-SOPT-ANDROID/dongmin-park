package com.sopt.now.data.remote.service

import com.sopt.now.data.dto.response.BaseResponseDto
import com.sopt.now.data.dto.response.ResponseUserInfo
import retrofit2.http.GET

interface HomeService {
    @GET("member/info")
    suspend fun getUserInfo(): BaseResponseDto<ResponseUserInfo>
}
