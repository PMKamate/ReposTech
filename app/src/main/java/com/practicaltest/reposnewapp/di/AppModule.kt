package com.practicaltest.reposnewapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.practicaltest.reposnewapp.data.local.AppDatabase
import com.practicaltest.reposnewapp.data.local.NewsDetailDao
import com.practicaltest.reposnewapp.data.remote.NewsRemoteDataSource
import com.practicaltest.reposnewapp.data.remote.NewsService
import com.practicaltest.reposnewapp.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(JacksonConverterFactory.create())
        .build()


    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideUserService(retrofit: Retrofit): NewsService = retrofit.create(
        NewsService::class.java
    )

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(newsService: NewsService) =
        NewsRemoteDataSource(newsService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.restaurantDao()


    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: NewsRemoteDataSource,
        newsDetailDao: NewsDetailDao
    ) =
        NewsRepository(remoteDataSource, newsDetailDao)

}