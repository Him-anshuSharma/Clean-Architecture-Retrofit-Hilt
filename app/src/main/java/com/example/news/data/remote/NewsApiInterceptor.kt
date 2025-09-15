package com.example.news.data.remote

import android.util.Log
import com.example.news.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset

class NewsApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Add API Key
        val newUrl = request.url.newBuilder()
            .addQueryParameter("apiKey", BuildConfig.API_KEY)
            .addQueryParameter("q", "India")
            .addQueryParameter("pageSize","100")
            .addQueryParameter("pageIn","1")
            .build()
        val newRequest = request.newBuilder().url(newUrl).build()

        // Log request
        logRequest(newRequest)

        // Proceed
        val response = chain.proceed(newRequest)

        // Log response
        logResponse(response)

        return response
    }

    private fun logRequest(request: Request) {
        Log.d("NewsApiInterceptor", "----- REQUEST -----")
        Log.d("NewsApiInterceptor", "${request.method} ${request.url}")
        Log.d("NewsApiInterceptor", "Headers: ${request.headers}")
        try {
            val buffer = Buffer()
            request.body?.writeTo(buffer)
            val charset = request.body?.contentType()?.charset(Charset.forName("UTF-8")) ?: Charset.forName("UTF-8")
            Log.d("NewsApiInterceptor", "Body: ${buffer.readString(charset)}")
        } catch (e: Exception) {
            Log.d("NewsApiInterceptor", "Failed to log request body: ${e.message}")
        }
    }

    private fun logResponse(response: Response) {
        Log.d("NewsApiInterceptor", "----- RESPONSE -----")
        Log.d("NewsApiInterceptor", "URL: ${response.request.url}")
        Log.d("NewsApiInterceptor", "Status: ${response.code}")
        Log.d("NewsApiInterceptor", "Headers: ${response.headers}")
        try {
            val responseBody = response.peekBody(Long.MAX_VALUE).string()
            Log.d("NewsApiInterceptor", "Body: $responseBody")
        } catch (e: Exception) {
            Log.d("NewsApiInterceptor", "Failed to log response body: ${e.message}")
        }
    }
}