package com.sopt.now.compose.domain.repository

import androidx.paging.PagingData
import com.sopt.now.compose.domain.entity.response.ReqresUserModel
import kotlinx.coroutines.flow.Flow

interface ReqresRepository {
    suspend fun getUserList(): Flow<PagingData<ReqresUserModel>>
}
