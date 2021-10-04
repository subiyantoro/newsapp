package com.subiyantoro.newsapp.usecases

import com.subiyantoro.newsapp.models.Articles

interface LocalInsertNewsArticle {
    suspend fun saveNewsArticle(article: Articles)
}