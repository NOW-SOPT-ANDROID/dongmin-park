package com.sopt.now.compose.data.remote.datasource

import com.sopt.now.compose.data.remote.dto.response.reqres.RequreUserList

interface ReqresDataSource {
    suspend fun getUserList(page: Int): RequreUserList
}
