package com.sopt.now.compose.domain.repository

import com.sopt.now.compose.domain.entity.request.RequestSignInEntity
import com.sopt.now.compose.domain.entity.request.RequestUserEntity

interface AuthRepository {
    suspend fun postSignUp(user: RequestUserEntity): String?
    suspend fun postSignIn(user: RequestSignInEntity): String?
}
