package com.subiyantoro.newsapp.usecases

import com.subiyantoro.newsapp.models.NewsResponse
import com.subiyantoro.newsapp.repositories.MainRepository
import retrofit2.Call
import javax.inject.Inject

class RemoteSearchNewsArticlesImpl @Inject constructor(var mainRepository: MainRepository) : RemoteSearchNewsArticles {
    override fun searchNews(q: String, country: String, category: String): Call<NewsResponse> = mainRepository.searchNewsArticles(q, country, category)
}