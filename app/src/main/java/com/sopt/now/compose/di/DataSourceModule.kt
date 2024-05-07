package com.sopt.now.compose.di

import com.sopt.now.compose.data.remote.datasource.ReqresDataSource
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
    abstract fun bindDataSource(dataSourceImpl: ReqresDataSourceImpl): ReqresDataSource
}
