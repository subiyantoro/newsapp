package com.subiyantoro.newsapp.models

data class NewsResponse(
    var status: String,
    var totalResults: Int?,
    var articles: List<Articles>?
)
