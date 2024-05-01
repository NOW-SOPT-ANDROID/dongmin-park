package com.sopt.now.compose.di

import com.sopt.now.compose.data.remote.repositoryImpl.AuthRepositoryImpl
import com.sopt.now.compose.data.remote.repositoryImpl.HomeRepositoryImpl
import com.sopt.now.compose.domain.repository.AuthRepository
import com.sopt.now.compose.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository
}
