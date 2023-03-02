package com.rbths.newstopheadlines.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rbths.newstopheadlines.model.ArticlesResponse
import com.rbths.newstopheadlines.network.ArticlesRepository
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.rbths.newstopheadlines.network.ArticlesRepositoryInterface
import com.rbths.newstopheadlines.network.RetrofitGenerator

class MainViewModel(repository: ArticlesRepositoryInterface = ArticlesRepository.instance()): ViewModel() {

    private val _articlesLiveData = MutableLiveData<ArticlesResponse>()
    val articlesLiveData: LiveData<ArticlesResponse> = _articlesLiveData

    var sourcesRepo = repository


    fun getHeadlines() = viewModelScope.launch {
        sourcesRepo.getSources().enqueue(object: Callback,
            retrofit2.Callback<ArticlesResponse> {
            override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {
                if(response.isSuccessful && response.body()!=null){
                    _articlesLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
            }

        })
    }
}