package com.sopt.now.compose.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseDto(
    val code: Int,
    val message: String,
)
