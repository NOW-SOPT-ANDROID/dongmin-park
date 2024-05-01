package com.sopt.now.compose.domain.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignInEntity(
    val authenticationId: String,
    val password: String
)
