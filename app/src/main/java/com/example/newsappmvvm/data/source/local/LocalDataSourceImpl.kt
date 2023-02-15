package com.example.newsappmvvm.data.source.local

import com.example.newsappmvvm.data.database.ArticleDao
import com.example.newsappmvvm.data.dto.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: ArticleDao
) : LocalDataSource {
    override suspend fun insert(article: Article): Long =
         dao.insert(article)


    override suspend fun delete(article: Article) {
        dao.delete(article)
    }

    override fun getSavedNews():Flow<List<Article>> =
        dao.getAllArticles()
}