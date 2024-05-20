package com.sopt.now.compose.domain.repository

import com.sopt.now.compose.domain.entity.response.ResponseUserInfo

interface HomeRepository {
    suspend fun getUserInfo(): Result<ResponseUserInfo>
}
