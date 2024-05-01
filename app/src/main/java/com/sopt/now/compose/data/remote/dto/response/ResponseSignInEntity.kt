package com.sopt.now.compose.data.remote.dto.response

import com.sopt.now.compose.domain.entity.response.ResponseUserInfo
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserDto(
    val authenticationId: String,
    val nickname: String,
    val phone: String,
)

fun ResponseUserDto.toResponseUserInfo() = ResponseUserInfo(
    authenticationId, nickname, phone
)
