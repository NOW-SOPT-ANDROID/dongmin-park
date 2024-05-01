package com.sopt.now.compose.domain.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestUserEntity(
    val authenticationId: String,
    val password: String,
    val nickname: String,
    val phone: String
)
