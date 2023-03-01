package com.rbths.newstopheadlines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.rbths.newstopheadlines.R
import com.rbths.newstopheadlines.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mViewModel.getHeadlines()

        mViewModel.sourcesLiveData.observe(this) {
            Log.i("mytag","oberver: $it")
        }
    }
}