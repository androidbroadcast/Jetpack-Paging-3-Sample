package dev.androidbroadcast.sample.paging3.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.androidbroadcast.sample.paging3.data.storage.model.ArticleDbo

@Database(entities = [ArticleDbo::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract val articleDao: ArticleDao
}