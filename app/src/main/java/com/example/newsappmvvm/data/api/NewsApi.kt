package com.example.newsappmvvm.data.api

import com.example.newsappmvvm.BuildConfig.API_KEY
import com.example.newsappmvvm.data.Resource
import com.example.newsappmvvm.data.dto.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode:String = "us",
        @Query("page") pageNumber:Int = 1,
        @Query("apiKey") apiKey:String = API_KEY
    ) : NewsResponse

    @GET("v2/everything")
    suspend fun getNewsWithSearchQuery(
        @Query("q") searchQuery:String,
        @Query("page") pageNumber:Int = 1,
        @Query("apiKey") apiKey:String = API_KEY
    ) : NewsResponse
}