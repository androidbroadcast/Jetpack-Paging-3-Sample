package dev.androidbroadcast.sample.paging3.data

import dev.androidbroadcast.sample.paging3.data.model.Article
import dev.androidbroadcast.sample.paging3.data.network.NewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService,
) {

    fun queryAll(query: String): Flow<List<Article>> {
        if (query.isBlank()) return flowOf(emptyList())

        return flow {
            val response = newsService.everything(query)
            emit(response.body()!!.articles)
        }
            .map { articles ->
                articles.map { article ->
                    article.toArticle()
                }
            }
    }
}