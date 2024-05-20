package com.sopt.now.compose.data.remote.dto.response

import com.sopt.now.compose.domain.entity.response.ResponseUserInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("phone")
    val phone: String,
)

fun ResponseUserDto.toResponseUserInfo() = ResponseUserInfo(
    authenticationId, nickname, phone
)
