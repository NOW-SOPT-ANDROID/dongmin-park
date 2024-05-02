package com.sopt.now.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserInfo(
    val authenticationId: String,
    val nickname: String,
    val phone: String,
)