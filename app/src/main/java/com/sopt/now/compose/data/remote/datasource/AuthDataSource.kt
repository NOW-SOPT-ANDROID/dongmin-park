package com.sopt.now.compose.data.remote.datasource

import com.sopt.now.compose.data.remote.dto.response.BaseResponseWithoutDataDto
import com.sopt.now.compose.domain.entity.request.RequestSignInEntity
import com.sopt.now.compose.domain.entity.request.RequestUserEntity
import retrofit2.Response

interface AuthDataSource {
    suspend fun postSignUp(user: RequestUserEntity): Response<BaseResponseWithoutDataDto>

    suspend fun postSignIn(user: RequestSignInEntity): Response<BaseResponseWithoutDataDto>
}
