package com.sopt.now.compose.domain.repository

import com.sopt.now.compose.domain.entity.response.ResponseUserList

interface ReqresRepository {
    suspend fun getUserList(page: Int = 1): Result<ResponseUserList>
}
