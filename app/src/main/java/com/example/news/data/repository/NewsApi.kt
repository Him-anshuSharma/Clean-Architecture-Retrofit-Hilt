package com.example.news.data.repository

import com.example.news.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/everything")
    suspend fun getArticles(@Query("q") country: String = "India"): NewsResponseDto

    @GET("/v2/everything")
    suspend fun getArticlesForCountry(@Query("q") country: String = "India"): NewsResponseDto
}