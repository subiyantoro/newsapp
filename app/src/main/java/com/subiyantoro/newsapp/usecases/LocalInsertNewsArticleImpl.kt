package com.subiyantoro.newsapp.usecases

import com.subiyantoro.newsapp.models.Articles
import com.subiyantoro.newsapp.repositories.MainRepository
import javax.inject.Inject

class LocalInsertNewsArticleImpl @Inject constructor(var mainRepository: MainRepository): LocalInsertNewsArticle {
    override suspend fun saveNewsArticle(article: Articles) = mainRepository.saveNewsArticle(article)
}