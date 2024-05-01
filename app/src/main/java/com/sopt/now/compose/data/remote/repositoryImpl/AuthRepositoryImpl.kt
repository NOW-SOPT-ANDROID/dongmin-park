package com.sopt.now.compose.data.remote.repositoryImpl

import com.sopt.now.compose.data.remote.dto.response.BaseResponseDto
import com.sopt.now.compose.data.remote.service.AuthService
import com.sopt.now.compose.domain.entity.request.RequestUserEntity
import com.sopt.now.compose.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
) : AuthRepository {
    override suspend fun postSignUp(user: RequestUserEntity): String? =
        runCatching {
            authService.signUp(user)
        }.fold(
            onSuccess = {
                it.headers()["location"]
            },
            onFailure = {
                null
            }
        )
}
