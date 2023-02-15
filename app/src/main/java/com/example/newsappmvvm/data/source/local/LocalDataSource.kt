package com.example.newsappmvvm.data.source.local

import com.example.newsappmvvm.data.dto.Article
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insert(article: Article) : Long
    suspend fun delete(article: Article)
    fun getSavedNews():Flow<List<Article>>
}