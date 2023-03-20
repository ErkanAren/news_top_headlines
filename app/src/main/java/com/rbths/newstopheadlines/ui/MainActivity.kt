package com.rbths.newstopheadlines.ui

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.rbths.newstopheadlines.R
import com.rbths.newstopheadlines.model.AppDatabase

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}