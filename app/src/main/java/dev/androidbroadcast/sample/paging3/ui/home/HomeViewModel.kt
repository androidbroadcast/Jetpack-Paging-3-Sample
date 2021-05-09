package dev.androidbroadcast.sample.paging3.ui.home

import androidx.lifecycle.*
import dev.androidbroadcast.sample.paging3.data.model.Article
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Provider
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class HomeViewModel @Inject constructor(
    private val queryNewsUseCaseProvider: Provider<QueryNewsUseCase>
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    @OptIn(FlowPreview::class)
    val news: StateFlow<List<Article>> = query.debounce(300)
        .flatMapLatest { query ->
            val queryNewsUseCase = queryNewsUseCaseProvider.get()
            queryNewsUseCase(query)
        }.stateIn(viewModelScope, started = SharingStarted.Lazily, emptyList())

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