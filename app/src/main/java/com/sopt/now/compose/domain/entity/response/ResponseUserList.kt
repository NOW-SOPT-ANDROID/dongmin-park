package com.sopt.now.compose.domain.entity.response

import com.sopt.now.compose.data.remote.dto.response.reqres.Data
import kotlinx.collections.immutable.ImmutableList

data class ResponseUserList(
    val userList: ImmutableList<UserData>,
) {
    data class UserData(
        val avatar: String,
        val email: String,
        val first_name: String,
        val id: Int,
        val last_name: String,
    )
}