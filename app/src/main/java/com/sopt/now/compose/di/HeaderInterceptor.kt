package com.sopt.now.compose.di

import com.sopt.now.compose.data.local.UserDataStore
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val dataStore: UserDataStore,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder().newAuthBuilder().build()

        return chain.proceed(newRequest)
    }

    private fun Request.Builder.newAuthBuilder() =
        this.addHeader(AUTHORIZATION, "$BEARER ${dataStore.userId}")

    companion object {
        private const val BEARER = "BEARER"
        private const val AUTHORIZATION = "Authorization"
    }
}