package com.example.newsappmvvm.data.source.remote

import com.example.newsappmvvm.data.Resource
import com.example.newsappmvvm.data.dto.NewsResponse

interface RemoteDataSource {

    suspend fun getBreakingNews(countryCode:String,pageNumber:Int):Resource<NewsResponse>

    suspend fun getNewsWithSearchQuery(searchQuery:String,pageNumber: Int):Resource<NewsResponse>

}