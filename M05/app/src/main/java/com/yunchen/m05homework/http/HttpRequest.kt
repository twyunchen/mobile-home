package com.yunchen.m05homework.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpRequest {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://119.27.190.226:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T> makeService(): T {
        return retrofit.create<T>(T::class.java)
    }
}