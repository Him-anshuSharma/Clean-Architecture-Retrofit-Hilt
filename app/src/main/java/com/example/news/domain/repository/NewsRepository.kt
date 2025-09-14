package com.example.news.domain.repository

import com.example.news.domain.model.Article

interface NewsRepository {
    suspend fun getNews() : List<Article>
    suspend fun getNewsByCountry(country: String): List<Article>
}