package dev.androidbroadcast.sample.paging3.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import dev.androidbroadcast.sample.paging3.data.model.Article
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Provider

class HomeViewModel @Inject constructor(
    private val queryNewsUseCaseProvider: Provider<QueryNewsUseCase>
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private var newPagingSource: PagingSource<*, *>? = null

    val news: StateFlow<PagingData<Article>> = query
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(query: String): Pager<Int, Article> {
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            newPagingSource?.invalidate()
            val queryNewsUseCase = queryNewsUseCaseProvider.get()
            queryNewsUseCase(query).also { newPagingSource = it }
        }
    }

    fun setQuery(query: String) {
        _query.tryEmit(query)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val viewModerProvider: Provider<HomeViewModel>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == HomeViewModel::class.java)
            return viewModerProvider.get() as T
        }
    }
}