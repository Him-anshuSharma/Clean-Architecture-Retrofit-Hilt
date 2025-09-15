package com.example.news.domain.usecase

import com.example.news.domain.model.Article
import com.example.news.domain.repository.NewsRepository
import com.example.news.utils.Resource
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke() : Resource<List<Article>> {
        return newsRepository.getNews()
    }
}