package com.example.news.data.mappers

import com.example.news.data.remote.dto.ArticleDto
import com.example.news.domain.model.Article


fun ArticleDto.toArticle(): Article {
    return Article(
        title = title ?: "N/A",
        description = description ?: "N/A",
        url = url ?: "N/A",
        urlToImage = urlToImage ?: "N/A",
        publishedAt = publishedAt ?: "N/A",
        content = content ?: "N/A"
    )
}
