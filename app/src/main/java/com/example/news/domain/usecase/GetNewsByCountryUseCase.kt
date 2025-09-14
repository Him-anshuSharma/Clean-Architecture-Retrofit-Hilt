package com.example.news.domain.usecase

import com.example.news.domain.model.Article
import com.example.news.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsByCountryUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(country: String): List<Article> {
        return newsRepository.getNewsByCountry(country)
    }

}