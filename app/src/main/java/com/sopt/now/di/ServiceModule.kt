package com.sopt.now.di

import com.sopt.now.data.remote.service.AuthService
import com.sopt.now.di.qualifier.AUTH
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

//    @Provides
//    @Singleton
//    fun provideMainService(@HEADER retrofit: Retrofit): HomeService =
//        retrofit.create(HomeService::class.java)
//
//    @Provides
//    @Singleton
//    fun provideReqresService(@REQRES retrofit: Retrofit): ReqresService =
//        retrofit.create(ReqresService::class.java)
}