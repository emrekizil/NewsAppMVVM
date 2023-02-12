package com.example.newsappmvvm.data.repository

import android.app.DownloadManager.Query
import androidx.lifecycle.LiveData
import com.example.newsappmvvm.data.dto.Article
import com.example.newsappmvvm.data.dto.NewsResponse
import retrofit2.Response

interface NewsRepository {

    suspend fun getBreakingNews(countryCode:String,pageNumber: Int) : Response<NewsResponse>

    suspend fun getNewsWithSearchQuery(searchQuery:String,pageNumber: Int) : Response<NewsResponse>

    suspend fun insert(article: Article) : Long

    suspend fun delete(article: Article)
    fun getSavedNews() : LiveData<List<Article>>
}