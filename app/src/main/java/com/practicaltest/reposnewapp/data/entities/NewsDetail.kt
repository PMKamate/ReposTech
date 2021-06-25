package com.practicaltest.reposnewapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "newsDetail")
class NewsDetail (
    @PrimaryKey
    var id: Int? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var sourceName: String? = null
)