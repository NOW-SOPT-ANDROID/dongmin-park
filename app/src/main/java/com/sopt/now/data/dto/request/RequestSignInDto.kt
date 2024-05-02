package com.sopt.now.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignInDto(
    val authenticationId: String,
    val password: String
)