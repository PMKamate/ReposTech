package com.practicaltest.reposnewapp.data.remote

import com.practicaltest.reposnewapp.model.news.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?
    ): Response<News>
}