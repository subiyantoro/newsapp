package com.subiyantoro.newsapp.usecases

import com.subiyantoro.newsapp.models.NewsResponse
import com.subiyantoro.newsapp.repositories.MainRepository
import retrofit2.Call
import javax.inject.Inject

class RemoteGetNewsArticlesImpl @Inject constructor(var mainRepository: MainRepository): RemoteGetNewsArticles {
    override fun getNewsArticlesList(
        country: String,
        category: String,
        page: Int
    ): Call<NewsResponse> = mainRepository.getNewsArticles(country, category, page)
}