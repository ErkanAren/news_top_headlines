package com.rbths.newstopheadlines.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonElement
import com.rbths.newstopheadlines.model.ArticlesResponse
import com.rbths.newstopheadlines.model.Source
import com.rbths.newstopheadlines.network.SourcesRepository
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel: ViewModel() {

    private val _sourcesLiveData = MutableLiveData<ArticlesResponse>()
    val sourcesLiveData: LiveData<ArticlesResponse> = _sourcesLiveData

    private var sourcesRepo : SourcesRepository//= SearchRepository.instance()


    init {
        sourcesRepo = SourcesRepository.instance()
    }
    fun getHeadlines(){
        sourcesRepo.getSources().enqueue(object: Callback,
            retrofit2.Callback<ArticlesResponse> {
            override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {
                if(response.isSuccessful && response.body()!=null){
                    _sourcesLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
            }

        })
    }
}