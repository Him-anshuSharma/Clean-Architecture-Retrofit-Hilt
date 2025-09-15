package com.example.news.domain.model

import java.time.LocalDateTime

data class Article(
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: LocalDateTime,
    val content: String
)
