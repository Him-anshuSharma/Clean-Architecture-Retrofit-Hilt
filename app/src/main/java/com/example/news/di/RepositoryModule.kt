package com.example.news.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.news.data.local.dao.ArticleDao
import com.example.news.data.remote.NewsApi
import com.example.news.data.repository.NewsRepositoryImpl
import com.example.news.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi, articleDao: ArticleDao): NewsRepository {
        return NewsRepositoryImpl(newsApi,articleDao)
    }
}