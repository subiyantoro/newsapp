package com.subiyantoro.newsapp.repositories

import com.subiyantoro.newsapp.datasource.ApiSourceData
import com.subiyantoro.newsapp.datasource.LocalSourceData

interface MainRepository: ApiSourceData, LocalSourceData