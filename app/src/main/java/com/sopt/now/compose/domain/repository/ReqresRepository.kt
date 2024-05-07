package com.sopt.now.compose.domain.repository

import androidx.paging.PagingData
import com.sopt.now.compose.domain.entity.response.ReqresUserData
import kotlinx.coroutines.flow.Flow

interface ReqresRepository {
    suspend fun getUserList(): Flow<PagingData<ReqresUserData>>
}
