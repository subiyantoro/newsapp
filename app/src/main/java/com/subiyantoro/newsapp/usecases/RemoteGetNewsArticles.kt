package com.subiyantoro.newsapp.usecases

import com.subiyantoro.newsapp.models.NewsResponse
import retrofit2.Call

interface RemoteGetNewsArticles {
    fun getNewsArticlesList(country: String, category: String, page: Int): Call<NewsResponse>
}