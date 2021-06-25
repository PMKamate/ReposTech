package com.practicaltest.reposnewapp.data.remote

import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val newsService: NewsService
) : BaseDataSource() {
    suspend fun getNews(country: String, apiKey: String) =
        getResult { newsService.getNews(country, apiKey) }
}