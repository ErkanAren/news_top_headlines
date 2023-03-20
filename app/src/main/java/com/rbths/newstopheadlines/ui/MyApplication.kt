package com.rbths.newstopheadlines.ui

import android.app.Application
import androidx.room.Room
import com.rbths.newstopheadlines.model.AppDatabase

class MyApplication:Application() {
    companion object{
        lateinit var DATABASE:AppDatabase
    }
    override fun onCreate() {
        super.onCreate()

        DATABASE = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "my_database"
        ).fallbackToDestructiveMigration().build()
    }
}