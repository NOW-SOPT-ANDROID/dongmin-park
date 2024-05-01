package com.sopt.now.compose.data.remote.dto.response.reqres

import kotlinx.serialization.Serializable

@Serializable
data class Support(
    val text: String,
    val url: String,
)
