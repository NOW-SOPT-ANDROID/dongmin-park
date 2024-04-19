package com.sopt.now.compose.di

import android.content.Context
import android.content.SharedPreferences
import com.sopt.now.compose.data.local.DataStore
import com.sopt.now.compose.data.local.DataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideGoingDataStore(dataStoreImpl: DataStoreImpl): DataStore =
        dataStoreImpl
}
