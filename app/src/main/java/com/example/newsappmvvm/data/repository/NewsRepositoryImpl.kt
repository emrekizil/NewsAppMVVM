package com.example.newsappmvvm.data.repository

import com.example.newsappmvvm.data.api.NewsApi
import com.example.newsappmvvm.data.database.ArticleDatabase
import com.example.newsappmvvm.data.dto.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val database: ArticleDatabase,
    private val api: NewsApi
) : NewsRepository {
    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Response<NewsResponse> =
        api.getBreakingNews(countryCode, pageNumber)

    override suspend fun getNewsWithSearchQuery(
        searchQuery: String,
        pageNumber: Int
    ): Response<NewsResponse> =
        api.getNewsWithSearchQuery(searchQuery, pageNumber)
}