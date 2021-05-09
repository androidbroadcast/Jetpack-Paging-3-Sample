package dev.androidbroadcast.sample.paging3.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    @SerialName("source") val source: SourceDto? = null,
    @SerialName("title") val title: String = "",
    @SerialName("url") val url: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("urlToImage") val urlToImage: String? = null,
    @SerialName("publishedAt") val publishedAt: String? = null,
    @SerialName("content") val content: String? = null,
)
