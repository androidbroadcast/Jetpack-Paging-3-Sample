package dev.androidbroadcast.sample.paging3.data.storage

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.androidbroadcast.sample.paging3.data.storage.model.ArticleDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun queryAll(): Flow<List<ArticleDbo>>

    @Query("SELECT * FROM articles WHERE query LIKE :query")
    fun query(query: String): PagingSource<Int, ArticleDbo>

    @Insert
    suspend fun insert(entities: List<ArticleDbo>)

    @Query("DELETE FROM articles WHERE query LIKE :query")
    suspend fun deleteAll(query: String)
}