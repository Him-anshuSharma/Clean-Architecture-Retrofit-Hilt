package com.example.news.domain.repository

import com.example.news.domain.model.Article
import com.example.news.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews() : Flow<List<Article>>
}