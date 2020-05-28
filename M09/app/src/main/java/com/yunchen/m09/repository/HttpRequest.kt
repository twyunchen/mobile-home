package com.yunchen.m09.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpRequest {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.adviceslip.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T> makeRepository(): T {
        return retrofit.create<T>(T::class.java)
    }
}