package com.rbths.newstopheadlines.network

import com.rbths.newstopheadlines.model.ArticlesResponse
import retrofit2.Call
import retrofit2.Response

class ArticlesRepository : ArticlesRepositoryInterface {
    var service: ApiGET

    init {
        val retrofit = RetrofitGenerator()
        service = retrofit.getRetrofitGenerator().create(ApiGET::class.java)
    }

    override fun getSources(): Call<ArticlesResponse> {
        return service.getSources()
    }

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