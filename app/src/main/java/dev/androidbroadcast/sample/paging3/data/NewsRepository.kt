package dev.androidbroadcast.sample.paging3.data

import androidx.paging.PagingSource
import dev.androidbroadcast.sample.paging3.data.model.Article
import dev.androidbroadcast.sample.paging3.data.network.EverythingNewsPagingSource
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val everythingNewsPagingSourceFactory: EverythingNewsPagingSource.Factory
) {

    fun queryAll(query: String): PagingSource<Int, Article> {
        return everythingNewsPagingSourceFactory.create(query)
    }
}