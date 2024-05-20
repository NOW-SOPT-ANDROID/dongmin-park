package com.sopt.now.compose.data.remote.datasourceimpl

import com.sopt.now.compose.data.remote.datasource.AuthDataSource
import com.sopt.now.compose.data.remote.dto.response.BaseResponseWithoutDataDto
import com.sopt.now.compose.data.remote.service.AuthService
import com.sopt.now.compose.domain.entity.request.RequestSignInEntity
import com.sopt.now.compose.domain.entity.request.RequestUserEntity
import retrofit2.Response
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthDataSource {
    override suspend fun postSignUp(user: RequestUserEntity): Response<BaseResponseWithoutDataDto> =
        authService.postSignUp(
            user
        )

    override suspend fun postSignIn(user: RequestSignInEntity): Response<BaseResponseWithoutDataDto> =
        authService.postSignIn(user)
}
