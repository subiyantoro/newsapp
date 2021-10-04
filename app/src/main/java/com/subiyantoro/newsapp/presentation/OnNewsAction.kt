package com.subiyantoro.newsapp.presentation

import com.subiyantoro.newsapp.models.Articles

interface OnNewsAction {
    fun onClickNews(articles: Articles)
}