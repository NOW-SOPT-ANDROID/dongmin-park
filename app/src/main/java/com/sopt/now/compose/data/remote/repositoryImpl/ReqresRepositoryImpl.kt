package com.sopt.now.compose.data.remote.repositoryImpl

import com.sopt.now.compose.data.remote.dto.response.reqres.toResponseUserList
import com.sopt.now.compose.data.remote.service.ReqresService
import com.sopt.now.compose.domain.entity.response.ResponseUserList
import com.sopt.now.compose.domain.repository.ReqresRepository
import javax.inject.Inject

class ReqresRepositoryImpl @Inject constructor(
    private val reqresService: ReqresService,
) : ReqresRepository {
    override suspend fun getUserList(page: Int): Result<ResponseUserList> =
        runCatching {
            reqresService.getUserInfo(page).toResponseUserList()
        }
}
