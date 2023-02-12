package com.example.newsappmvvm.data.dto

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)