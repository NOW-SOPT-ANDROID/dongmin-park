package com.sopt.now.data.remote.repository

import com.sopt.now.data.dto.request.RequestSignInDto
import com.sopt.now.data.dto.request.RequestSignUpDto
import com.sopt.now.data.remote.service.AuthService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
) {
    suspend fun postSignUp(user: RequestSignUpDto): String? =
        runCatching {
            authService.postSignUp(user)
        }.fold(
            onSuccess = {
                it.headers()["location"]
            },
            onFailure = {
                null
            }
        )

    suspend fun getSignIn(user: RequestSignInDto): Result<Unit> =
        runCatching {
            authService.postSignIn(user)
        }
}
