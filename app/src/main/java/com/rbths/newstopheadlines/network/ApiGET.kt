package com.rbths.newstopheadlines.network

import com.rbths.newstopheadlines.model.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiGET {
    /**
     * e.g. /v2/top-headlines?sources=bbc-news
     */
    @GET("v2/top-headlines")
    fun getArticles(@Query(value = "sources", encoded = true) sourceId:String): Call<ArticlesResponse>
}