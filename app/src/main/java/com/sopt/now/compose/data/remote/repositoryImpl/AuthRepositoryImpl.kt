package com.sopt.now.compose.data.remote.repositoryImpl

import com.sopt.now.compose.data.remote.service.AuthService
import com.sopt.now.compose.domain.entity.request.RequestSignInEntity
import com.sopt.now.compose.domain.entity.request.RequestUserEntity
import com.sopt.now.compose.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
) : AuthRepository {
    override suspend fun postSignUp(user: RequestUserEntity): String? =
        runCatching {
            authService.postSignUp(user)
        }.fold(
            onSuccess = {
                it.headers()[HEADER]
            },
            onFailure = {
                null
            }
        )

    override suspend fun postSignIn(user: RequestSignInEntity): Result<Unit> =
        runCatching {
            authService.postSignIn(user)
        }

    companion object {
        private const val HEADER = "location"
    }
}
