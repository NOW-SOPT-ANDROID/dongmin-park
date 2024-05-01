package com.sopt.now.compose.data.remote.dto.response.reqres

import com.sopt.now.compose.domain.entity.response.ResponseUserList
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequreUserList(
    @SerialName("data")
    val data: List<Data>,
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    val support: Support,
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int,
)

fun RequreUserList.toResponseUserList() = ResponseUserList(
    data.map { it.toUserData() }.toPersistentList()
)
