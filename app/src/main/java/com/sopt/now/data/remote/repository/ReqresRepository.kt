package com.sopt.now.data.remote.repository

import com.sopt.now.data.remote.dto.response.reqres.RequreUserList
import com.sopt.now.data.remote.service.ReqresService
import javax.inject.Inject

class ReqresRepository @Inject constructor(
    private val reqresService: ReqresService,
) {
    suspend fun getUserList(page: Int): Result<RequreUserList> =
        runCatching {
            reqresService.getUserInfo(page)
        }
}
