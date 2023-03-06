package com.rbths.newstopheadlines.network

import com.rbths.newstopheadlines.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitGenerator {
    private var retrofit: Retrofit


    init {
        val interceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("X-Api-Key", BuildConfig.API_KEY)
                .build()
            chain.proceed(newRequest)
        }

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        // 1. Create a Retrofit client and using the interface make the call
        retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun getRetrofitGenerator():Retrofit{
        return retrofit
    }
}