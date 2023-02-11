package com.example.newsappmvvm.data.repository

import android.app.DownloadManager.Query
import com.example.newsappmvvm.data.dto.NewsResponse
import retrofit2.Response

interface NewsRepository {

    suspend fun getBreakingNews(countryCode:String,pageNumber: Int) : Response<NewsResponse>

    suspend fun getNewsWithSearchQuery(searchQuery:String,pageNumber: Int) : Response<NewsResponse>
}