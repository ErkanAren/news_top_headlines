package com.rbths.newstopheadlines.network

import com.google.gson.JsonElement
import com.rbths.newstopheadlines.model.ArticlesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiGET {
    @GET("v2/top-headlines?sources=bbc-news")
    fun getSources(): Call<ArticlesResponse>
}