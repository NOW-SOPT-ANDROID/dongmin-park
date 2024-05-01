package com.sopt.now.compose.di

import com.sopt.now.compose.data.remote.service.AuthService
import com.sopt.now.compose.di.qualifier.AUTH
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideAuthService(@AUTH retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}
