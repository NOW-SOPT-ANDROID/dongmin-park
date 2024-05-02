package com.sopt.now.data.remote.service

import com.sopt.now.data.dto.request.RequestSignInDto
import com.sopt.now.data.dto.request.RequestSignUpDto
import com.sopt.now.data.dto.response.BaseResponseDto
import com.sopt.now.data.dto.response.ResponseUserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun postSignUp(
        @Body user: RequestSignUpDto,
    ): Response<BaseResponseDto<Unit>>

    @POST("member/login")
    suspend fun postSignIn(
        @Body user: RequestSignInDto,
    ): BaseResponseDto<ResponseUserDto>
}
