package com.subiyantoro.newsapp.di

import android.content.Context
import androidx.room.Room
import com.subiyantoro.newsapp.datasource.ApiSourceData
import com.subiyantoro.newsapp.datasource.ApiSourceDataImpl
import com.subiyantoro.newsapp.datasource.LocalSourceData
import com.subiyantoro.newsapp.datasource.LocalSourceDataImpl
import com.subiyantoro.newsapp.models.AppDatabase
import com.subiyantoro.newsapp.repositories.MainRepository
import com.subiyantoro.newsapp.repositories.MainRepositoryImpl
import com.subiyantoro.newsapp.services.NewsDaoService
import com.subiyantoro.newsapp.services.NewsService
import com.subiyantoro.newsapp.usecases.*
import com.subiyantoro.newsapp.utils.Env
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {
    @Provides
    fun provideBindApiService(): NewsService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Env.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(NewsService::class.java)
    }

    @Provides
    fun provideArticleOffline(@ApplicationContext context: Context): NewsDaoService {
        return Room.databaseBuilder(context, AppDatabase::class.java, "Articles")
            .build().daoNews()
    }

    @Provides
    fun provideApiSourceData(newsService: NewsService): ApiSourceData {
        return ApiSourceDataImpl(newsService)
    }

    @Provides
    fun provideLocalSourceData(newsDaoService: NewsDaoService): LocalSourceData {
        return LocalSourceDataImpl(newsDaoService)
    }

    @Provides
    fun provideMainRepository(apiSourceData: ApiSourceData, localSourceData: LocalSourceData): MainRepository = MainRepositoryImpl(apiSourceData, localSourceData)

    @Provides
    fun provideRemoteGetNewsArticles(mainRepository: MainRepository): RemoteGetNewsArticles = RemoteGetNewsArticlesImpl(mainRepository)

    @Provides
    fun provideLocalGetNewsArticles(mainRepository: MainRepository): LocalGetNewsArticles = LocalGetNewsArticlesImpl(mainRepository)

    @Provides
    fun provideInsertNewsArticle(mainRepository: MainRepository): LocalInsertNewsArticle = LocalInsertNewsArticleImpl(mainRepository)

    @Provides
    fun provideRemoteSearchNewsArticles(mainRepository: MainRepository): RemoteSearchNewsArticles = RemoteSearchNewsArticlesImpl(mainRepository)

    @Provides
    fun provideLocalDeleteNewsArticle(mainRepository: MainRepository): LocalDeleteNewsArticle = LocalDeleteNewsArticleImpl(mainRepository)
}