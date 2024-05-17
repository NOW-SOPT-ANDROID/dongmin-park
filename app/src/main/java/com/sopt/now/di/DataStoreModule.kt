package com.sopt.now.di

import com.sopt.now.data.local.UserDataStore
import com.sopt.now.data.local.UserDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {
    @Binds
    @Singleton
    abstract fun bindDataStore(dataStoreImpl: UserDataStoreImpl): UserDataStore
}
