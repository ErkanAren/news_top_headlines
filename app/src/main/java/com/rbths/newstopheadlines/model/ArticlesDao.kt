package com.rbths.newstopheadlines.model

import androidx.room.*

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM article")
    suspend fun getAll(): List<Article>

    //@Query("SELECT * FROM article WHERE uid IN (:userIds)")
    //fun loadAllByIds(userIds: IntArray): List<Article>

    //@Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
    //        "last_name LIKE :last LIMIT 1")
    //fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Delete
    suspend fun delete(user: Article)
}
