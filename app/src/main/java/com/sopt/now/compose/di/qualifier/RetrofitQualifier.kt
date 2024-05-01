package com.sopt.now.compose.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AUTH

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HEADER

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class REQRES
