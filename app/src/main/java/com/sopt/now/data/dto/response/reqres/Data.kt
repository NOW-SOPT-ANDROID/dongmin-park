package com.sopt.now.data.dto.response.reqres

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val avatar: String,
    val email: String,
    @SerialName("first_name")
    val first_name: String,
    val id: Int,
    @SerialName("last_name")
    val last_name: String,
)
