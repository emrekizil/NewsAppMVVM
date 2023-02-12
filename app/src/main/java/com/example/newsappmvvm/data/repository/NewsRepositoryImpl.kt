package com.example.newsappmvvm.data.repository

import androidx.lifecycle.LiveData
import com.example.newsappmvvm.data.api.NewsApi
import com.example.newsappmvvm.data.database.ArticleDatabase
import com.example.newsappmvvm.data.dto.Article
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

    override suspend fun insert(article: Article): Long {
       return database.getArticleDao().insert(article)
    }

    override suspend fun delete(article: Article) {
        database.getArticleDao().delete(article)
    }

    override fun getSavedNews(): LiveData<List<Article>> {
        return database.getArticleDao().getAllArticles()
    }


}