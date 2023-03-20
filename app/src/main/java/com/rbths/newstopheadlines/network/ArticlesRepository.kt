package com.rbths.newstopheadlines.network

import androidx.room.Room
import com.rbths.newstopheadlines.model.ArticlesResponse
import retrofit2.Call
import com.rbths.newstopheadlines.BuildConfig
import com.rbths.newstopheadlines.model.AppDatabase
import com.rbths.newstopheadlines.model.Article
import com.rbths.newstopheadlines.ui.MyApplication.Companion.DATABASE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticlesRepository : ArticlesRepositoryInterface {
    var service: ApiGET

    private val db = DATABASE

    init {
        val retrofit = RetrofitGenerator()

        service = retrofit.getRetrofitGenerator().create(ApiGET::class.java)

    }


    suspend fun saveArticles(articles: List<Article>) {
    }
    override fun getArticles(): Call<ArticlesResponse> {
        return service.getArticles(BuildConfig.SOURCE_ID)
    }

    override suspend fun saveArticlesToLocal(articleList: List<Article>)= withContext(Dispatchers.IO)  {
        db.articleDao().insertAll(articleList)
    }

    // Singleton ArticlesRepository
    companion object {
        private var sInstance: ArticlesRepository? = null

        fun instance(): ArticlesRepository {
            if (sInstance == null) {
                synchronized(ArticlesRepository) {
                    sInstance = ArticlesRepository()
                }
            }
            return sInstance!!
        }
    }
}