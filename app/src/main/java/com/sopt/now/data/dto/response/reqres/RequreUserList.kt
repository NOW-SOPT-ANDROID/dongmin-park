package com.sopt.now.data.dto.response.reqres

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
