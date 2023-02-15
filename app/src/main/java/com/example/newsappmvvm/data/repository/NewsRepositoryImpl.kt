package com.example.newsappmvvm.data.repository

import androidx.lifecycle.LiveData
import com.example.newsappmvvm.data.Resource
import com.example.newsappmvvm.data.api.NewsApi
import com.example.newsappmvvm.data.database.ArticleDatabase
import com.example.newsappmvvm.data.dto.Article
import com.example.newsappmvvm.data.dto.NewsResponse
import com.example.newsappmvvm.data.source.local.LocalDataSource
import com.example.newsappmvvm.data.source.remote.RemoteDataSource
import com.example.newsappmvvm.data.source.remote.RemoteDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource:RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {
    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Flow<Resource<NewsResponse>> = flow {
        emit(Resource.Loading())
        when(val response = remoteDataSource.getBreakingNews(countryCode,pageNumber)){
            is Resource.Error -> emit(response)
            is Resource.Success -> emit(response)
            is Resource.Loading -> Unit
        }
    }

    override suspend fun getNewsWithSearchQuery(
        searchQuery: String,
        pageNumber: Int
    ): Flow<Resource<NewsResponse>> =  flow {
        emit(Resource.Loading())
        when (val response = remoteDataSource.getNewsWithSearchQuery(searchQuery, pageNumber)) {
            is Resource.Error -> emit(response)
            is Resource.Success -> emit(response)
            is Resource.Loading -> Unit
        }
    }

    override suspend fun insert(article: Article): Long =
        localDataSource.insert(article)


    override suspend fun delete(article: Article) {
        localDataSource.delete(article)
    }

    override fun getSavedNews(): Flow<List<Article>> = localDataSource.getSavedNews()
    /*override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Response<NewsResponse> =
        api.getBreakingNews(countryCode, pageNumber)

    override suspend fun getNewsWithSearchQuery(
        searchQuery: String,
        pageNumber: Int
    ): Response<NewsResponse> =
        api.getNewsWithSearchQuery(searchQuery, pageNumber)*/

    /*override suspend fun insert(article: Article): Long {
       return database.getArticleDao().insert(article)
    }

    override suspend fun delete(article: Article) {
        database.getArticleDao().delete(article)
    }

    override fun getSavedNews(): LiveData<List<Article>> {
        return database.getArticleDao().getAllArticles()
    }*/


}