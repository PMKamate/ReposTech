package com.practicaltest.reposnewapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.practicaltest.reposnewapp.data.entities.NewsDetail
import com.practicaltest.reposnewapp.data.repository.NewsRepository
import com.practicaltest.reposnewapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    fun getNewsDetails(country: String, apiKey: String): LiveData<Resource<List<NewsDetail>>> {
        Log.d("Test: ","con: "+country+" key: "+apiKey)
        return repository.getNewsDetails(country, apiKey)
    }
}
