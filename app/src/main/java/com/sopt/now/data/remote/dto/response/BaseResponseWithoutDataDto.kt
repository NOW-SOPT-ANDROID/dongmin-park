package com.sopt.now.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseWithoutDataDto(
    val code: Int,
    val message: String,
)
