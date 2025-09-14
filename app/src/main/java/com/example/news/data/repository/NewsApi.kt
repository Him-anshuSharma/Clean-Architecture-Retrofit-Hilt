package com.example.news.data.repository

import com.example.news.data.repository.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/everything")
    suspend fun getArticles(): NewsResponseDto

    @GET("/v2/everything")
    suspend fun getArticlesForIndia(@Query("country") country: String = "in"): NewsResponseDto
}