package com.rbths.newstopheadlines.viewmodel

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MainViewModel(repository: ArticlesRepositoryInterface = ArticlesRepository.instance()): ViewModel() {

    private val _articlesLiveData = MutableLiveData<ArticlesResponse>()
    val articlesLiveData: LiveData<ArticlesResponse> = _articlesLiveData

    var articlesRepo = repository

    /**
     * It returns the headlines of the selected source and adds them to the articlesLiveData as ArticlesResponse
     */
    fun getHeadlines() = viewModelScope.launch{
        articlesRepo.getArticles().enqueue(object: Callback,
            retrofit2.Callback<ArticlesResponse> {
            override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {
                if(response.isSuccessful && response.body()!=null){
                    _articlesLiveData.value = response.body()

                    viewModelScope.launch {
                        articlesRepo.saveArticlesToLocal(response.body()?.articles!!)
                    }

                }
            }
            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
            }

        })
    }
}