package dev.androidbroadcast.sample.paging3.ui.home

import androidx.paging.PagingSource
import dev.androidbroadcast.sample.paging3.data.NewsRepository
import dev.androidbroadcast.sample.paging3.data.model.Article
import javax.inject.Inject

class QueryNewsUseCase @Inject constructor(private val repository: NewsRepository) {

    operator fun invoke(query: String): PagingSource<Int, Article> {
        return repository.queryAll(query)
    }
}