package com.rbths.newstopheadlines.network

import com.rbths.newstopheadlines.model.Article
import com.rbths.newstopheadlines.model.ArticlesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

interface ArticlesRepositoryInterface {
    fun getArticles(): Call<ArticlesResponse>

    suspend fun getArticlesFromLocal():  List<Article>

    suspend fun deleteAll()

    suspend fun saveArticlesToLocal(articleList:List<Article>)

}