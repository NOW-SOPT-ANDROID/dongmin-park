package com.sopt.now.compose.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseWithoutDataDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
)
