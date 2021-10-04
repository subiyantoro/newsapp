package com.subiyantoro.newsapp.usecases

import com.subiyantoro.newsapp.models.Articles
import com.subiyantoro.newsapp.repositories.MainRepository
import javax.inject.Inject

class LocalDeleteNewsArticleImpl @Inject constructor(var mainRepository: MainRepository) : LocalDeleteNewsArticle {
    override suspend fun deleteNewsArticleFromDB(articles: Articles) = mainRepository.deleteNewsArticle(articles)
}