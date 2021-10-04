package com.subiyantoro.newsapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subiyantoro.newsapp.models.Articles
import com.subiyantoro.newsapp.models.NewsResponse
import com.subiyantoro.newsapp.usecases.LocalGetNewsArticles
import com.subiyantoro.newsapp.usecases.RemoteGetNewsArticles
import com.subiyantoro.newsapp.usecases.RemoteSearchNewsArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var remoteGetNewsArticles: RemoteGetNewsArticles,
    var localGetNewsArticles: LocalGetNewsArticles,
    var remoteSearchNewsArticles: RemoteSearchNewsArticles): ViewModel()
{
    var newsFromAPI = MutableLiveData<NewsResponse>()
    var newsFromDB = MutableLiveData<List<Articles>>()
    var isLoading = MutableLiveData<Boolean>()
    var isError = MutableLiveData<Boolean>(false)
    fun getNewsFromApi(country: String, category: String, page: Int) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            remoteGetNewsArticles.getNewsArticlesList(country, category, page).enqueue(object: Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    val data = response.body()
                    isLoading.postValue(false)
                    if (response.isSuccessful) {
                        data?.let {
                            newsFromAPI.postValue(it)
                            isError.postValue(false)
                        }
                    } else {
                        isError.postValue(true)
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    isLoading.postValue(false)
                    isError.postValue(true)
                    newsFromAPI.postValue(
                        NewsResponse(
                            "error",
                            null,
                            null
                        )
                    )
                }

            })
        }
    }
    fun getNewsFromDB() {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            localGetNewsArticles.getNewsArticles().let {
                newsFromDB.postValue(it)
            }
        }
    }
    fun searchNewsFromApi(q: String) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            remoteSearchNewsArticles.searchNews(q, "id", "technology").enqueue(object: Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    val data = response.body()
                    isLoading.postValue(false)
                    if (response.isSuccessful) {
                        data?.let {
                            newsFromAPI.postValue(it)
                            isError.postValue(false)
                        }
                    } else {
                        isError.postValue(true)
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    isLoading.postValue(false)
                    isError.postValue(true)
                    newsFromAPI.postValue(
                        NewsResponse(
                            "error",
                            null,
                            null
                        )
                    )
                }

            })
        }
    }
}