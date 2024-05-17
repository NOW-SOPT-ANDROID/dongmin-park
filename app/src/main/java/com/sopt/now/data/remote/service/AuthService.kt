package com.sopt.now.data.remote.service

import com.sopt.now.data.remote.dto.request.RequestSignInDto
import com.sopt.now.data.remote.dto.request.RequestSignUpDto
import com.sopt.now.data.remote.dto.response.BaseResponseWithoutDataDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun postSignUp(
        @Body user: RequestSignUpDto,
    ): Response<BaseResponseWithoutDataDto>

    @POST("member/login")
    suspend fun postSignIn(
        @Body user: RequestSignInDto,
    ): Response<BaseResponseWithoutDataDto>
}
