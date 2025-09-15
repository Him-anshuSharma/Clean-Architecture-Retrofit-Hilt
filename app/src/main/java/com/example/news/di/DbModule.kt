package com.example.news.di

import android.content.Context
import androidx.room.Room
import com.example.news.data.local.AppDatabase
import com.example.news.data.local.dao.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "news_db"
        ).build()
    }

    @Singleton
    @Provides
    fun getArticleDao(appDatabase: AppDatabase): ArticleDao{
        return appDatabase.articleDao()
    }

}