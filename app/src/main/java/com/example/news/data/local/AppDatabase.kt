package com.example.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.news.data.local.dao.ArticleDao
import com.example.news.data.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}