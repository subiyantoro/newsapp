package com.subiyantoro.newsapp.datasource

import androidx.lifecycle.LiveData
import com.subiyantoro.newsapp.models.Articles

interface LocalSourceData {
    fun getNewsArticles(): List<Articles>
    suspend fun saveNewsArticle(articles: Articles)
    suspend fun getNewsByTitle(title: String): Articles
    suspend fun deleteNewsArticle(articles: Articles)
}