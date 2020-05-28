package com.yunchen.m09.repository

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl("https://api.adviceslip.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()
}


val repositoryModule = module {
    single { provideRetrofit() }
    single { provideSlipRepository(get()) }
}

fun provideSlipRepository(retrofit: Retrofit): SlipRepository =
    retrofit.create(SlipRepository::class.java)
