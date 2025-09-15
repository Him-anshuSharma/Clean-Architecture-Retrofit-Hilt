package com.example.news.utils

suspend fun <T> safeApiCall(call : suspend () -> T): Resource<T> {
    return try {
        Resource.Success(call())
    }catch (e: Exception){
        Resource.Error(e.message,null)
    }
}