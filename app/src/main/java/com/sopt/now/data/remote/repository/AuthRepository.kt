package com.sopt.now.data.remote.repository

import com.sopt.now.data.remote.dto.request.RequestSignInDto
import com.sopt.now.data.remote.dto.request.RequestSignUpDto
import com.sopt.now.data.remote.service.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor(
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

    suspend fun postSignIn(user: RequestSignInDto): String? =
        runCatching {
            authService.postSignIn(user)
        }.fold(
            onSuccess = {
                it.headers()["location"]
            },
            onFailure = {
                null
            }
        )
}
