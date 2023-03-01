package com.rbths.newstopheadlines.network

import earen.com.diamondbettingtips.network.LiveDataCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitGenerator {
    private var retrofit: Retrofit
    //var service: ApiGET

    init {
        //header jwt token
        val interceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("X-Api-Key", "8238c1be0009439f9d590439a68cdc86")
                .build()
            chain.proceed(newRequest)
        }


        val okHttpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        // 1. Create a Retrofit client and using the interface make the call
        retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .client(okHttpClient)

            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    fun getRetrofitGenerator():Retrofit{
        return retrofit
    }
}