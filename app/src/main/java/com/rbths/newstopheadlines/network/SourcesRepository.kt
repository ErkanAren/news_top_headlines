package com.rbths.newstopheadlines.network

import android.content.Context
import com.google.gson.JsonElement
import retrofit2.Call

class SourcesRepository {
    lateinit var service: ApiGET

    init {
        val retrofit = RetrofitGenerator()
        service = retrofit.getRetrofitGenerator().create(ApiGET::class.java)
    }

    fun getSources(): Call<JsonElement> {
        return service.getSources()
    }

    companion object {
        private var sInstance: SourcesRepository? = null

        fun instance(): SourcesRepository {
            if (sInstance == null) {
                synchronized(SourcesRepository) {
                    sInstance = SourcesRepository()
                }
            }
            return sInstance!!
        }
    }
}