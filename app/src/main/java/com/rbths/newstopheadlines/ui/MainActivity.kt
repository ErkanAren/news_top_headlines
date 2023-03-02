package com.rbths.newstopheadlines.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rbths.newstopheadlines.R
import com.rbths.newstopheadlines.adapters.ArticlesAdapter
import com.rbths.newstopheadlines.model.Article
import com.rbths.newstopheadlines.utils.Utils
import com.rbths.newstopheadlines.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: MainViewModel
    lateinit var articlesRecyclerView: RecyclerView
    lateinit var articlesAdapter: ArticlesAdapter

    var articlesList = mutableListOf<Article>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        articlesRecyclerView = findViewById(R.id.articlesRV)
        setupRecyclerView()

        //we are going to call the the headlines
        mViewModel.getHeadlines()

        //articles will be called after getHeadlines returns a successful response
        mViewModel.articlesLiveData.observe(this) { articlesResponse ->
            if(articlesResponse.status == "ok"){
                articlesList.clear()
                if(!articlesResponse.articles.isNullOrEmpty()) {
                    Log.i("mytag","articlesResponse.articles: ${articlesResponse.articles}")
                    //the articles were sorted but I added this code to sort them since it was in the requirements of
                    articlesList.addAll(articlesResponse.articles.sortedBy { Utils.getLongFromISO8601(it.publishedAt) }.reversed())
                }
                articlesAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setupRecyclerView() {
        articlesAdapter = ArticlesAdapter(this,articlesList)
        articlesRecyclerView.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        articlesRecyclerView.setAdapter(articlesAdapter)
    }


}