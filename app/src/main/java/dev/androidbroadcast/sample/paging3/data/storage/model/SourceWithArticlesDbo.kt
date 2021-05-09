package dev.androidbroadcast.sample.paging3.data.storage.model

import androidx.room.Embedded
import androidx.room.Relation
import dev.androidbroadcast.sample.paging3.data.storage.model.ArticleDbo
import dev.androidbroadcast.sample.paging3.data.storage.model.SourceDbo

data class SourceWithArticlesDbo(
    @Embedded val sourceDbo: SourceDbo,
    @Relation(
        parentColumn = "id",
        entityColumn = "sourceId"
    )
    val articles: List<ArticleDbo>
)
