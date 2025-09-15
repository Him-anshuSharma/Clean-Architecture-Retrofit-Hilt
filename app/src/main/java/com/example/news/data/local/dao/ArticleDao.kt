package com.example.news.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDateTime

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<ArticleEntity>)

    @Query("Select * from articles order by publishedAt limit 100")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Query(
        """
            DELETE FROM articles WHERE id IN (
            SELECT id FROM articles 
            ORDER BY publishedAt ASC 
            LIMIT 100
            )
        """
    )
    fun removeOldArticles() : Unit

    @Query("Select max(publishedAt) from articles")
    fun getLastestArticleDate(): LocalDateTime?
}