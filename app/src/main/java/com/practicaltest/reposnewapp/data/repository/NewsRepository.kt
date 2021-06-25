package com.practicaltest.reposnewapp.data.repository

import android.util.Log
import com.practicaltest.reposnewapp.data.entities.NewsDetail
import com.practicaltest.reposnewapp.data.local.NewsDetailDao
import com.practicaltest.reposnewapp.data.remote.NewsRemoteDataSource
import com.practicaltest.reposnewapp.model.news.Article
import com.practicaltest.reposnewapp.utils.NewsListModel
import com.practicaltest.reposnewapp.utils.performGetOperation
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val newsDetailDao: NewsDetailDao) {

    fun getNewsDetails(country: String, apiKey: String) = performGetOperation(
        databaseQuery = { newsDetailDao.getAllUserDataItem() },
        networkCall = { remoteDataSource.getNews(country,apiKey) },
        saveCallResult = {
            it.articles?.let { it1 ->
                Log.d("Test nwsize: ", "" + it1.size)

                val list = getNewsDBList(it1)
                newsDetailDao.insertAll(list.restaurantItemList)
            }
        }
    )

    fun getNewsDBList(articleListResponse: List<Article?>): NewsListModel {
        val newsList = ArrayList<NewsDetail>()
        var id =1
        articleListResponse.forEach { item ->
            id++
            newsList.add(
                NewsDetail(
                    id,
                    item?.author,
                    item?.title,
                    item?.description,
                    item?.url,
                    item?.urlToImage,
                    item?.publishedAt,
                    item?.source?.name
                )
            )
        }
        Log.d("Test dbsize: ", "" + newsList.size)
        return NewsListModel(newsList)

    }

}