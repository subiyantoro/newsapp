package com.subiyantoro.newsapp.usecases

import androidx.lifecycle.LiveData
import com.subiyantoro.newsapp.models.Articles
import com.subiyantoro.newsapp.repositories.MainRepository
import javax.inject.Inject

class LocalGetNewsArticlesImpl @Inject constructor(var mainRepository: MainRepository): LocalGetNewsArticles {
    override fun getNewsArticles(): List<Articles> = mainRepository.getNewsArticles()
    override suspend fun getNewsByTitle(title: String): Articles = mainRepository.getNewsByTitle(title)
}