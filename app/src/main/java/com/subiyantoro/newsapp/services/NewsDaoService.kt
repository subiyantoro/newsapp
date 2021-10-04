package com.subiyantoro.newsapp.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.subiyantoro.newsapp.models.Articles

@Dao
interface NewsDaoService {
    @Query("SELECT * FROM Articles")
    fun getNewsFromDB(): List<Articles>

    @Insert(onConflict = REPLACE)
    suspend fun saveNewsToDB(articles: Articles)

    @Delete
    suspend fun deleteNewsFromDB(articles: Articles)

    @Query("SELECT * FROM Articles WHERE title=:title")
    suspend fun getNewsByTitle(title: String): Articles
}