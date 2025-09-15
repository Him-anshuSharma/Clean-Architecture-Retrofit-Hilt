package com.example.news.data.remote

import com.example.news.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/everything")
    suspend fun getArticles(): NewsResponseDto

    @GET("/v2/everything")
    suspend fun getLatestArticleByTimestamp(@Query("from") from: String): NewsResponseDto


}