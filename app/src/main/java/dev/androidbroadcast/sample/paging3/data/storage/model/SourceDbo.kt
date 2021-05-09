package dev.androidbroadcast.sample.paging3.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sources")
data class SourceDbo(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "sourceId") val sourceId: String,
    @ColumnInfo(name = "name") val name: String,
)