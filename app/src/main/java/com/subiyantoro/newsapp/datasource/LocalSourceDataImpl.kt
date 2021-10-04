package com.subiyantoro.newsapp.datasource

import androidx.lifecycle.LiveData
import com.subiyantoro.newsapp.models.Articles
import com.subiyantoro.newsapp.services.NewsDaoService

class LocalSourceDataImpl(var newsDaoService: NewsDaoService): LocalSourceData {
    override fun getNewsArticles(): List<Articles> = newsDaoService.getNewsFromDB()
    override suspend fun saveNewsArticle(articles: Articles) = newsDaoService.saveNewsToDB(articles)
    override suspend fun getNewsByTitle(title: String): Articles = newsDaoService.getNewsByTitle(title)
    override suspend fun deleteNewsArticle(articles: Articles) = newsDaoService.deleteNewsFromDB(articles)
}