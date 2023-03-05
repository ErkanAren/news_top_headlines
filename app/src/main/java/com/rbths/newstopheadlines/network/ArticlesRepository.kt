package com.rbths.newstopheadlines.network

import com.rbths.newstopheadlines.model.ArticlesResponse
import retrofit2.Call
import com.rbths.newstopheadlines.BuildConfig

class ArticlesRepository : ArticlesRepositoryInterface {
    var service: ApiGET

    init {
        val retrofit = RetrofitGenerator()
        service = retrofit.getRetrofitGenerator().create(ApiGET::class.java)
    }


    override fun getArticles(): Call<ArticlesResponse> {
        return service.getArticles(BuildConfig.SOURCE_ID)
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