package com.example.news.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.news.data.local.entity.ArticleEntity
import com.example.news.data.remote.dto.ArticleDto
import com.example.news.domain.model.Article
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun ArticleDto.toArticle(): Article {
    return Article(
        title = title ?: "N/A",
        description = description ?: "N/A",
        url = url ?: "N/A",
        urlToImage = urlToImage ?: "N/A",
        publishedAt = dtoDateConverter(publishedAt),
        content = content ?: "N/A"
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun dtoDateConverter(date:String?): LocalDateTime{
    if(date?.isNotEmpty() == true){
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
    }
    return LocalDateTime.now()
}

fun ArticleEntity.toArticle(): Article{
    return Article(
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun ArticleDto.toArticleEntity(): ArticleEntity{
    return ArticleEntity(
        title = title ?: "N/A",
        description = description ?: "N/A",
        url = url ?: "N/A",
        urlToImage = urlToImage ?: "N/A",
        publishedAt = dtoDateConverter(publishedAt),
        content = content ?: "N/A"
    )
}

