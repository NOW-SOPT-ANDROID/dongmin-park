package com.sopt.now.compose.data.remote.service

import com.sopt.now.compose.data.remote.dto.response.reqres.RequreUserList
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresService {
    @GET("users")
    suspend fun getUserInfo(@Query("page") page: Int): RequreUserList
}
