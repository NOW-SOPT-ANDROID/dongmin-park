package com.sopt.now.di

import com.sopt.now.data.local.UserDataStore
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
        this.addHeader(NAME, dataStore.userId)

    companion object {
        private const val NAME = "memberId"
    }
}
