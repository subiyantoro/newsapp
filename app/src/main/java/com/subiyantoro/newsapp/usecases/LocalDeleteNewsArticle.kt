package com.subiyantoro.newsapp.usecases

import com.subiyantoro.newsapp.models.Articles

interface LocalDeleteNewsArticle {
    suspend fun deleteNewsArticleFromDB(articles: Articles)
}