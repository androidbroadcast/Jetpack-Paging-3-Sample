package dev.androidbroadcast.sample.paging3.data.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dev.androidbroadcast.sample.paging3.data.model.Article
import dev.androidbroadcast.sample.paging3.data.network.model.ArticleDto
import dev.androidbroadcast.sample.paging3.data.storage.NewsDatabase
import dev.androidbroadcast.sample.paging3.data.toArticleDbo
import retrofit2.HttpException

@ExperimentalPagingApi
class EverythingNewsRemoteMediator @AssistedInject constructor(
    private val newsDatabase: NewsDatabase,
    private val newsService: NewsService,
    @Assisted("query") private val query: String,
) : RemoteMediator<Int, Article>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult = when (loadType) {
        LoadType.REFRESH -> refresh(state)
        LoadType.PREPEND -> prepend(state)
        LoadType.APPEND -> append(state)
    }

    private suspend fun refresh(state: PagingState<Int, Article>): MediatorResult {
        val response = newsService.everything(query, page = INITIAL_PAGE_NUMBER, pageSize(state))

        if (response.isSuccessful) {
            deleteCache()
            val articles = response.body()!!.articles
            saveCache(articles)
            return MediatorResult.Success(endOfPaginationReached = articles.isEmpty())
        } else {
            return MediatorResult.Error(HttpException(response))
        }
    }

    private fun prepend(state: PagingState<Int, Article>): MediatorResult {
        return MediatorResult.Success(endOfPaginationReached = false)
    }

    private suspend fun append(state: PagingState<Int, Article>): MediatorResult {
        val page = state.pages.minOfOrNull { it.prevKey ?: Int.MAX_VALUE }
            ?: return MediatorResult.Success(endOfPaginationReached = true)

        val response = newsService.everything(query, page, pageSize(state))

        if (response.isSuccessful) {
            val articles = response.body()!!.articles
            saveCache(articles)
            return MediatorResult.Success(endOfPaginationReached = articles.isEmpty())
        } else {
            return MediatorResult.Error(HttpException(response))
        }
    }

    private fun pageSize(state: PagingState<Int, Article>): Int {
        return state.config.pageSize.coerceAtMost(NewsService.MAX_PAGE_SIZE)
    }

    private suspend fun deleteCache() {
        newsDatabase.articleDao.deleteAll(query)
    }

    private suspend fun saveCache(articles: List<ArticleDto>) {
        newsDatabase.articleDao
            .insert(articles.map { it.toArticleDbo(query) })
    }

    private companion object {

        private const val INITIAL_PAGE_NUMBER = 1
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted("query") query: String): EverythingNewsRemoteMediator
    }
}