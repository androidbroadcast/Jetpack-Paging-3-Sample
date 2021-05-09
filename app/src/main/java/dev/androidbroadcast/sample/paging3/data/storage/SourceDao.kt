package dev.androidbroadcast.sample.paging3.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import dev.androidbroadcast.sample.paging3.data.storage.model.SourceDbo
import dev.androidbroadcast.sample.paging3.data.storage.model.SourceWithArticlesDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface SourceDao {

    @Query("SELECT * FROM sources")
    fun queryAll(): Flow<List<SourceDbo>>

    @Insert
    suspend fun insert(entities: List<SourceDbo>)

    @Transaction
    @Query("SELECT * FROM sources")
    fun querySourceAndLibraries(): Flow<List<SourceWithArticlesDbo>>
}