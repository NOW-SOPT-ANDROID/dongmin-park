package com.sopt.now.compose.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShardPreferenceModule {
    @Provides
    @Singleton
    fun provideUserSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
}
