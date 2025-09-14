package com.example.news.data.repository

import com.example.news.data.mappers.toArticle
import com.example.news.data.remote.dto.ArticleDto
import com.example.news.domain.model.Article
import com.example.news.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val newsApi: NewsApi) : NewsRepository {
    override suspend fun getNews(): List<Article> {
        return newsApi.getArticles().articles.map { it.toArticle() }
    }

    override suspend fun getNewsByCountry(country:String): List<Article> {
        return newsApi.getArticlesForCountry(country).articles.map { it.toArticle() }
    }
}