package com.rbths.newstopheadlines.network

import com.rbths.newstopheadlines.model.ArticlesResponse
import retrofit2.Call
import retrofit2.Response

interface ArticlesRepositoryInterface {
    fun getSources(): Call<ArticlesResponse>
}