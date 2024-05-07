package com.sopt.now.compose.domain.repository

import com.sopt.now.compose.domain.entity.request.RequestSignInEntity
import com.sopt.now.compose.domain.entity.request.RequestUserEntity

interface AuthRepository {
    suspend fun postSignUp(user: RequestUserEntity): String?
    suspend fun postSignIn(user: RequestSignInEntity): String?
    fun getId(): String
    fun getPassword(): String
    fun getNickname(): String
    fun getPhoneNumber(): String
    fun setId(id: String)
    fun setPassword(password: String)
    fun setNickname(nickname: String)
    fun setPhoneNumber(phoneNumber: String)
    fun setUserId(userId: String)
}
