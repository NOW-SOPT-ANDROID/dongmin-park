package com.sopt.now.compose.data.remote.datasourceimpl

import com.sopt.now.compose.data.remote.datasource.ReqresDataSource
import com.sopt.now.compose.data.remote.dto.response.reqres.RequreUserList
import com.sopt.now.compose.data.remote.service.ReqresService
import javax.inject.Inject

class ReqresDataSourceImpl @Inject constructor(
    private val reqresService: ReqresService,
) : ReqresDataSource {
    override suspend fun getUserList(page: Int): RequreUserList =
        reqresService.getUserInfo(page)
}
