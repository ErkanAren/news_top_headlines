package com.rbths.newstopheadlines.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonElement
import com.rbths.newstopheadlines.model.Source
import com.rbths.newstopheadlines.network.SourcesRepository
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel: ViewModel() {

    private val _sourcesLiveData = MutableLiveData<JsonElement>()
    val sourcesLiveData: LiveData<JsonElement> = _sourcesLiveData

    private var sourcesRepo : SourcesRepository//= SearchRepository.instance()


    init {
        sourcesRepo = SourcesRepository.instance()
    }
    fun getHeadlines(){
        sourcesRepo.getSources().enqueue(object: Callback,
            retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if(response.isSuccessful && response.body()!=null){
                    Log.i("mytag","response: $response")
                    _sourcesLiveData.value = response.body()
                }else{
                    Log.i("mytag","response failed: $response")

                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.i("mytag","onFailure: ${t.message}")

            }

        })
    }
}