package dev.androidbroadcast.sample.paging3.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.androidbroadcast.sample.paging3.data.storage.model.ArticleDbo
import dev.androidbroadcast.sample.paging3.data.storage.model.ArticleWithSourceDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun queryAll(): Flow<List<ArticleDbo>>

    @Insert
    suspend fun insert(entities: List<ArticleDbo>)

    @Query("SELECT * FROM articles")
    fun queryArticleWithSource(): Flow<List<ArticleWithSourceDbo>>
}