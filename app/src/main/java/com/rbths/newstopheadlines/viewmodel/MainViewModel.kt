package com.rbths.newstopheadlines.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbths.newstopheadlines.model.ArticlesResponse
import com.rbths.newstopheadlines.network.ArticlesRepository
import com.rbths.newstopheadlines.network.ArticlesRepositoryInterface
import com.rbths.newstopheadlines.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel(repository: ArticlesRepositoryInterface = ArticlesRepository.instance()): ViewModel() {

    private val _articlesLiveData = MutableLiveData<ArticlesResponse>()
    val articlesLiveData: LiveData<ArticlesResponse> = _articlesLiveData

    var articlesRepo = repository

    /**
     * It returns the headlines of the selected source and adds them to the articlesLiveData as ArticlesResponse
     */
    fun getHeadlines() = viewModelScope.launch{
        // we will call the local database here and post the value to our livedata which is observed in ArticleListFragment
        val articlesLocal = async {  articlesRepo.getArticlesFromLocal() }
        val localArticResponse = async {ArticlesResponse(Constants.OK_STATUS,articlesLocal.await().size,articlesLocal.await())}
        _articlesLiveData.postValue(localArticResponse.await())


        articlesRepo.getArticles().enqueue(object: Callback,
            retrofit2.Callback<ArticlesResponse> {
            override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {
                if(response.isSuccessful && response.body()!=null){
                    _articlesLiveData.value = response.body()

                    viewModelScope.launch {
                        // we will clear our local database here and then add the new articles
                        articlesRepo.deleteAll()
                        articlesRepo.saveArticlesToLocal(response.body()?.articles!!)
                    }

                }
            }
            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
            }

        })
    }
}