package com.subiyantoro.newsapp.datasource

import com.subiyantoro.newsapp.models.NewsResponse
import com.subiyantoro.newsapp.services.NewsService
import retrofit2.Call

class ApiSourceDataImpl(var newsService: NewsService): ApiSourceData {
    override fun getNewsArticles(country: String, category: String, page: Int): Call<NewsResponse> = newsService.getTopHeadlineNews(country, category, page)
    override fun searchNewsArticles(
        q: String,
        country: String,
        category: String
    ): Call<NewsResponse> = newsService.searchTopHeadlineNews(q, country, category)
}