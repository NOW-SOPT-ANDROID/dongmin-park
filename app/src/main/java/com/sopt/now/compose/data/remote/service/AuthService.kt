package com.sopt.now.compose.data.remote.service

import com.sopt.now.compose.data.remote.dto.response.BaseResponseDto
import com.sopt.now.compose.data.remote.dto.response.ResponseUserDto
import com.sopt.now.compose.domain.entity.request.RequestSignInEntity
import com.sopt.now.compose.domain.entity.request.RequestUserEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun postSignUp(
        @Body user: RequestUserEntity,
    ): Response<BaseResponseDto<Unit>>

    @POST("member/login")
    suspend fun postSignIn(
        @Body user: RequestSignInEntity
    ): BaseResponseDto<ResponseUserDto>
}
