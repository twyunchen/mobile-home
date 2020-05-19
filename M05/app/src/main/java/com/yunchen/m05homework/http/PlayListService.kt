package com.yunchen.m05homework.http

import com.yunchen.m05homework.dtos.GetPersonalizedPlayListResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayListService {
    @GET("/personalized")
    fun getPersonalizedPlayList():Call<GetPersonalizedPlayListResult>
}