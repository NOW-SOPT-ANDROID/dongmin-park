package com.sopt.now.compose.domain.repository

import com.sopt.now.compose.data.remote.dto.response.BaseResponseWithoutDataDto
import com.sopt.now.compose.domain.entity.request.RequestSignInEntity
import com.sopt.now.compose.domain.entity.request.RequestUserEntity
import retrofit2.Response

interface AuthRepository {
    suspend fun postSignUp(user: RequestUserEntity): Result<Response<BaseResponseWithoutDataDto>>
    suspend fun postSignIn(user: RequestSignInEntity): Result<Response<BaseResponseWithoutDataDto>>
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
