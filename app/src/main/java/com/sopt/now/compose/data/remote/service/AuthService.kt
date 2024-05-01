package com.sopt.now.compose.data.remote.service

import com.sopt.now.compose.data.remote.dto.BaseResponseDto
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun signUp(

    ): BaseResponseDto
}