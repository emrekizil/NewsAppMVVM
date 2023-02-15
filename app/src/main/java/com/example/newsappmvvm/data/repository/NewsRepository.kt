package com.example.newsappmvvm.data.repository

import android.app.DownloadManager.Query
import androidx.lifecycle.LiveData
import com.example.newsappmvvm.data.Resource
import com.example.newsappmvvm.data.dto.Article
import com.example.newsappmvvm.data.dto.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsRepository {

    suspend fun getBreakingNews(countryCode:String,pageNumber: Int) : Flow<Resource<NewsResponse>>

    suspend fun getNewsWithSearchQuery(searchQuery:String,pageNumber: Int) : Flow<Resource<NewsResponse>>

    suspend fun insert(article: Article) : Long

    suspend fun delete(article: Article)
    fun getSavedNews() : Flow<List<Article>>
}