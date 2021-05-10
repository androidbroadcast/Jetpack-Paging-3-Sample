package dev.androidbroadcast.sample.paging3.data

import dev.androidbroadcast.sample.paging3.data.model.Article
import dev.androidbroadcast.sample.paging3.data.model.Source
import dev.androidbroadcast.sample.paging3.data.network.model.ArticleDto
import dev.androidbroadcast.sample.paging3.data.network.model.SourceDto

internal fun ArticleDto.toArticle(): Article {
    return Article(
        source = this.source?.toSource(),
        title = title,
        url = url,
        description = description,
        author = author,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

private fun SourceDto.toSource(): Source {
    return Source(id = id, name = name)
}
