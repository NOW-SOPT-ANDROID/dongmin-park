package com.sopt.now.data.dto.response.reqres

import kotlinx.serialization.Serializable

@Serializable
data class Support(
    val text: String,
    val url: String,
)
