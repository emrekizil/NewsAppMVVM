package com.example.newsappmvvm.data.source.remote

import com.example.newsappmvvm.data.Resource
import com.example.newsappmvvm.data.api.NewsApi
import com.example.newsappmvvm.data.dto.NewsResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: NewsApi
) : RemoteDataSource {
    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Resource<NewsResponse> {
        return try {
            val response = api.getBreakingNews(countryCode,pageNumber)
            Resource.Success(response)
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getNewsWithSearchQuery(
        searchQuery: String,
        pageNumber: Int
    ): Resource<NewsResponse> {
        return try {
            val response = api.getNewsWithSearchQuery(searchQuery,pageNumber)
            Resource.Success(response)
        }catch (e:Exception){
            return Resource.Error(e.message.toString())
        }
    }
}