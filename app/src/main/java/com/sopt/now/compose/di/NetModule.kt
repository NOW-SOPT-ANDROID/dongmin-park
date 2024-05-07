package com.sopt.now.compose.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.compose.BuildConfig.BASE_URL
import com.sopt.now.compose.BuildConfig.REQRES_URL
import com.sopt.now.compose.di.qualifier.AUTH
import com.sopt.now.compose.di.qualifier.HEADER
import com.sopt.now.compose.di.qualifier.REQRES
import com.sopt.now.compose.ext.isJsonArray
import com.sopt.now.compose.ext.isJsonObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetModule {
    private const val APPLICATION_JSON = "application/json"
    private const val OKHTTP = "okhttp"
    private const val SPACING = 4

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    @Provides
    @Singleton
    fun provideJsonConverter(json: Json): Converter.Factory =
        json.asConverterFactory(APPLICATION_JSON.toMediaType())

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor { message ->
        when {
            message.isJsonObject() ->
                Timber.tag(OKHTTP).d(JSONObject(message).toString(SPACING))

            message.isJsonArray() ->
                Timber.tag(OKHTTP).d(JSONObject(message).toString(SPACING))

            else -> {
                Timber.tag(OKHTTP).d("CONNECTION INFO -> $message")
            }
        }
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    @HEADER
    fun provideHeaderInterceptor(headerInterceptor: HeaderInterceptor): Interceptor =
        headerInterceptor

    @Provides
    @Singleton
    @AUTH
    fun provideOkHttpClient(
        loggingInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    @HEADER
    fun provideHeaderOkHttpClient(
        loggingInterceptor: Interceptor,
        @HEADER headerInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(headerInterceptor)
        .build()


    @Provides
    @Singleton
    @AUTH
    fun provideAuthRetrofit(
        @AUTH client: OkHttpClient,
        factory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(factory)
        .build()

    @Provides
    @Singleton
    @HEADER
    fun provideHeaderRetrofit(
        @HEADER client: OkHttpClient,
        factory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(factory)
        .build()

    @Provides
    @Singleton
    @REQRES
    fun provideReqresRetrofit(
        @AUTH client: OkHttpClient,
        factory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(REQRES_URL)
        .client(client)
        .addConverterFactory(factory)
        .build()
}
