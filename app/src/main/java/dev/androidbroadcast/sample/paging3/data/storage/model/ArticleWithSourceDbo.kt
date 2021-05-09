package dev.androidbroadcast.sample.paging3.data.storage.model

import androidx.room.Embedded
import androidx.room.Relation
import dev.androidbroadcast.sample.paging3.data.storage.model.ArticleDbo
import dev.androidbroadcast.sample.paging3.data.storage.model.SourceDbo

data class ArticleWithSourceDbo(
    @Embedded val article: ArticleDbo,
    @Relation(
        parentColumn = "sourceId",
        entityColumn = "id"
    )
    val source: SourceDbo
)
