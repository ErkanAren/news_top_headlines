package com.rbths.newstopheadlines.network

import com.rbths.newstopheadlines.model.ArticlesResponse
import retrofit2.Call

interface ArticlesRepositoryInterface {
    fun getArticles(): Call<ArticlesResponse>
}