package com.subiyantoro.newsapp.usecases

import com.subiyantoro.newsapp.models.NewsResponse
import retrofit2.Call

interface RemoteSearchNewsArticles {
    fun searchNews(q: String, country: String, category: String): Call<NewsResponse>
}