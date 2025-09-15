package com.example.news.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.geometry.Rect
import com.example.news.data.local.dao.ArticleDao
import com.example.news.data.mappers.toArticle
import com.example.news.data.mappers.toArticleEntity
import com.example.news.data.remote.NewsApi
import com.example.news.domain.model.Article
import com.example.news.domain.repository.NewsRepository
import com.example.news.utils.Resource
import com.example.news.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class NewsRepositoryImpl @Inject constructor(val newsApi: NewsApi, val articleDao: ArticleDao) : NewsRepository {

    override fun getNews(): Flow<List<Article>> {
        return articleDao.getArticles().map { l->
            l.map {
                it.toArticle()
            }
        }
    }

    suspend fun fetchNews(){
        val response = safeApiCall { newsApi.getArticles() }
        if(response is Resource.Success){
            articleDao.insertArticles(response.data!!.articles.map { it.toArticleEntity() })
//            articleDao.removeOldArticles()
        }
    }
}