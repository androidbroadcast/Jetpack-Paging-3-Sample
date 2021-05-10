package dev.androidbroadcast.sample.paging3.data

import dev.androidbroadcast.sample.paging3.data.model.Article
import dev.androidbroadcast.sample.paging3.data.model.Source
import dev.androidbroadcast.sample.paging3.data.network.model.ArticleDto
import dev.androidbroadcast.sample.paging3.data.storage.model.ArticleDbo

internal fun ArticleDto.toArticle(): Article {
    return Article(
        title = title,
        url = url,
        description = description,
        author = author,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

internal fun ArticleDto.toArticleDbo(query: String): ArticleDbo {
    return ArticleDbo(
        id = 0,
        title = title,
        url = url,
        description = description,
        author = author,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        query = query
    )
}