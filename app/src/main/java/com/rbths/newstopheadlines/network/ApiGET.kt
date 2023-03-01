package com.rbths.newstopheadlines.network

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiGET {
    //@GET("v2/top-headlines/search/{keyword}")
    //fun searchInServer(@Path(value = "patient_id", encoded = true) patientId:String, @Path(value = "keyword", encoded = true) keyword:String): Call<JsonElement>
    @GET("v2/top-headlines?country=us")
    fun getSources(): Call<JsonElement>
}