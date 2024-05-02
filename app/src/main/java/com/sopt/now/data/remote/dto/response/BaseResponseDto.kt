package com.sopt.now.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseDto<T>(
    val code: Int,
    val message: String,
    val data: T
)
