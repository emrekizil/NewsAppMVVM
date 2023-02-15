package com.example.newsappmvvm.di

import android.content.Context
import androidx.room.Room
import com.example.newsappmvvm.data.database.ArticleDao
import com.example.newsappmvvm.data.database.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideArticleDao(articleDatabase: ArticleDatabase):ArticleDao{
        return articleDatabase.getArticleDao()
    }

    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext appContext: Context) : ArticleDatabase{
        return Room.databaseBuilder(
            appContext,
            ArticleDatabase::class.java,
            "article_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}