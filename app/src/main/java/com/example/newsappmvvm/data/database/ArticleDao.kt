package com.example.newsappmvvm.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsappmvvm.data.dto.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article) : Long

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * from articles")
    fun getAllArticles(): Flow<List<Article>>

}