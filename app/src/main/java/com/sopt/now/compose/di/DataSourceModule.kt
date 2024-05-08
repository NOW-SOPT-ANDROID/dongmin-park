package com.sopt.now.compose.di

import com.sopt.now.compose.data.remote.datasource.AuthDataSource
import com.sopt.now.compose.data.remote.datasource.HomeDataSource
import com.sopt.now.compose.data.remote.datasource.ReqresDataSource
import com.sopt.now.compose.data.remote.datasourceimpl.AuthDataSourceImpl
import com.sopt.now.compose.data.remote.datasourceimpl.HomeDataSourceImpl
import com.sopt.now.compose.data.remote.datasourceimpl.ReqresDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindReqresDataSource(dataSourceImpl: ReqresDataSourceImpl): ReqresDataSource

    @Binds
    @Singleton
    abstract fun bindHomeDataSource(dataSourceImpl: HomeDataSourceImpl): HomeDataSource

    @Binds
    @Singleton
    abstract fun bindAuthDataSource(dataSourceImpl: AuthDataSourceImpl): AuthDataSource
}
