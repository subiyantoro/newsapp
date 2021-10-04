package com.subiyantoro.newsapp.repositories

import androidx.lifecycle.LiveData
import com.subiyantoro.newsapp.datasource.ApiSourceData
import com.subiyantoro.newsapp.datasource.LocalSourceData
import com.subiyantoro.newsapp.models.Articles
import com.subiyantoro.newsapp.models.NewsResponse
import retrofit2.Call

class MainRepositoryImpl(var apiSourceData: ApiSourceData, var localSourceData: LocalSourceData): MainRepository {
    override fun getNewsArticles(country: String, category: String, page: Int): Call<NewsResponse> = apiSourceData.getNewsArticles(country, category, page)
    override fun getNewsArticles(): List<Articles> = localSourceData.getNewsArticles()
    override fun searchNewsArticles(
        q: String,
        country: String,
        category: String
    ): Call<NewsResponse> = apiSourceData.searchNewsArticles(q, country, category)
    override suspend fun saveNewsArticle(articles: Articles) = localSourceData.saveNewsArticle(articles)
    override suspend fun getNewsByTitle(title: String): Articles = localSourceData.getNewsByTitle(title)
    override suspend fun deleteNewsArticle(articles: Articles) = localSourceData.deleteNewsArticle(articles)
}