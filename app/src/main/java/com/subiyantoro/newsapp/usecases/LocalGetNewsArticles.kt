package com.subiyantoro.newsapp.usecases

import androidx.lifecycle.LiveData
import com.subiyantoro.newsapp.models.Articles

interface LocalGetNewsArticles {
    fun getNewsArticles(): List<Articles>
    suspend fun getNewsByTitle(title: String): Articles
}