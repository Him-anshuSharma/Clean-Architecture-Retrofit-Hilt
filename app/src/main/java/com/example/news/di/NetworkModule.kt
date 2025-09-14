package com.example.news.di

import com.example.news.BuildConfig
import com.example.news.data.repository.NewsApi
import com.example.news.data.repository.NewsApiInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://newsapi.org"

    @Provides
    @Singleton
    fun provideApiKey():String{
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    fun provideNewsApiInterceptor(): NewsApiInterceptor {
        return NewsApiInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(newsApiInterceptor: NewsApiInterceptor): OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(newsApiInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideNewApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }


}