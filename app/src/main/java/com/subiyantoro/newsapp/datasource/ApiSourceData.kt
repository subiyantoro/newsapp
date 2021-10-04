package com.subiyantoro.newsapp.datasource

import com.subiyantoro.newsapp.models.NewsResponse
import retrofit2.Call

interface ApiSourceData {
    fun getNewsArticles(country: String, category: String, page: Int): Call<NewsResponse>
    fun searchNewsArticles(q: String, country: String, category: String): Call<NewsResponse>
}