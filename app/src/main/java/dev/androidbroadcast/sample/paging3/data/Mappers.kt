package dev.androidbroadcast.sample.paging3.data

import dev.androidbroadcast.sample.paging3.data.model.Article
import dev.androidbroadcast.sample.paging3.data.model.Source
import dev.androidbroadcast.sample.paging3.data.network.model.ArticleDto
import dev.androidbroadcast.sample.paging3.data.network.model.SourceDto
import dev.androidbroadcast.sample.paging3.data.storage.model.ArticleWithSourceDbo
import dev.androidbroadcast.sample.paging3.data.storage.model.SourceDbo

internal fun ArticleWithSourceDbo.toArticle(): Article {
    return Article(
        source = this.source.toSource(),
        title = article.title,
        url = article.url,
        description = article.description,
        author = article.author,
        urlToImage = article.urlToImage,
        publishedAt = article.publishedAt,
        content = article.content
    )
}

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

internal fun SourceDbo.toSource(): Source {
    return Source(id = sourceId, name = name)
}
