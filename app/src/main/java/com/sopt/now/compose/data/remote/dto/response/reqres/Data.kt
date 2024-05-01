package com.sopt.now.compose.data.remote.dto.response.reqres

import com.sopt.now.compose.domain.entity.response.ResponseUserList
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

fun Data.toUserData() = ResponseUserList.UserData(
    avatar, email, first_name, id, last_name
)
