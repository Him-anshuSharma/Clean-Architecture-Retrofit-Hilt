package com.example.news.domain.model

import com.example.news.data.remote.dto.SourceDto

data class Article(
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
