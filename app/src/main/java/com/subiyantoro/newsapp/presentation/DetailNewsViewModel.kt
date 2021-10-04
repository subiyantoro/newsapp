package com.subiyantoro.newsapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subiyantoro.newsapp.models.Articles
import com.subiyantoro.newsapp.usecases.LocalDeleteNewsArticle
import com.subiyantoro.newsapp.usecases.LocalGetNewsArticles
import com.subiyantoro.newsapp.usecases.LocalInsertNewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailNewsViewModel @Inject constructor(
    var getNewsArticles: LocalGetNewsArticles,
    var insertNewsArticle: LocalInsertNewsArticle,
    var deleteNewsArticle: LocalDeleteNewsArticle): ViewModel() {
    var articleNews = MutableLiveData<Articles>()

    fun checkNewsOnDB(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            articleNews.postValue(getNewsArticles.getNewsByTitle(title))
        }
    }

    fun saveNewsToDB(articles: Articles) {
        viewModelScope.launch(Dispatchers.IO) {
            insertNewsArticle.saveNewsArticle(articles)
            checkNewsOnDB(articles.title)
        }
    }

    fun deleteNewsFromDB(articles: Articles) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNewsArticle.deleteNewsArticleFromDB(articles)
            checkNewsOnDB(articles.title)
        }
    }
}