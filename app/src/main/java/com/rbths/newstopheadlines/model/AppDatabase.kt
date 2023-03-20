package com.rbths.newstopheadlines.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Article::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticlesDao
}
