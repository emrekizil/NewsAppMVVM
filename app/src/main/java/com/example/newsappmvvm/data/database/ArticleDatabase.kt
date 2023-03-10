package com.example.newsappmvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsappmvvm.data.dto.Article

@Database(entities = [Article::class], version = 3, exportSchema = true)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase(){
    abstract fun getArticleDao():ArticleDao
}