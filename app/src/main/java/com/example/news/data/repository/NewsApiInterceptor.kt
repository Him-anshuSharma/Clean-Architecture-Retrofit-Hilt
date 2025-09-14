package com.example.news.data.repository

import com.example.news.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NewsApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url

        val newUrl = url.newBuilder().addQueryParameter("apiKey", BuildConfig.API_KEY).build()
        val newRequest = request.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}