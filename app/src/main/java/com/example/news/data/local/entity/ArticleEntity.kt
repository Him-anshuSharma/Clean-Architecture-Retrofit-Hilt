package com.example.news.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "articles")
data class ArticleEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: LocalDateTime,
    val content: String
)