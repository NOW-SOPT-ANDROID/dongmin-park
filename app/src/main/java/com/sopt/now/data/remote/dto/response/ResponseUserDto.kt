package com.sopt.now.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserDto(
    val authenticationId: String,
    val nickname: String,
    val phone: String,
)
