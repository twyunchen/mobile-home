package com.yunchen.m09.repository

import com.yunchen.m09.model.SlipResponse
import retrofit2.Call
import retrofit2.http.GET

interface SlipRepository {
    @GET("advice")
    fun getSlip(): Call<SlipResponse>
}