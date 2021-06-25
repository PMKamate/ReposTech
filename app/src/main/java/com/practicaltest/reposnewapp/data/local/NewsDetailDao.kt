package com.practicaltest.reposnewapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practicaltest.reposnewapp.data.entities.NewsDetail

@Dao
interface NewsDetailDao {

    @Query("SELECT * FROM newsDetail")
    fun getAllUserDataItem() : LiveData<List<NewsDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsDetail: List<NewsDetail>)

}