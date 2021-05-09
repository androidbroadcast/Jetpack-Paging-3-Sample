package dev.androidbroadcast.sample.paging3.ui.home

import dev.androidbroadcast.sample.paging3.data.NewsRepository
import dev.androidbroadcast.sample.paging3.data.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QueryNewsUseCase @Inject constructor(private val repository: NewsRepository) {

    operator fun invoke(query: String): Flow<List<Article>> {
        try {
            return repository.queryAll(query)
        } catch (e: Exception) {
            throw e
        }
    }
}