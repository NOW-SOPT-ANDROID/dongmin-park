package com.sopt.now.data.remote.dto.response.reqres

import com.sopt.now.model.Profile
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

fun Data.toFriend() = Profile.FriendProfile(
    profileImage = avatar,
    name = first_name + last_name,
    selfDescription = email
)
