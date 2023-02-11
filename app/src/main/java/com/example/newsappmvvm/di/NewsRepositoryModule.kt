package com.example.newsappmvvm.di

import com.example.newsappmvvm.data.repository.NewsRepository
import com.example.newsappmvvm.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsRepositoryModule {

   @Binds
   @ViewModelScoped
   abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl):NewsRepository
}