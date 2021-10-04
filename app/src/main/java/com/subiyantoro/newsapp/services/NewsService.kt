package com.subiyantoro.newsapp.services

import com.subiyantoro.newsapp.models.NewsResponse
import com.subiyantoro.newsapp.utils.Env
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun getTopHeadlineNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = Env.API_KEY
    ): Call<NewsResponse>

    @GET("top-headlines")
    fun searchTopHeadlineNews(
        @Query("q") q: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = Env.API_KEY
    ): Call<NewsResponse>
}