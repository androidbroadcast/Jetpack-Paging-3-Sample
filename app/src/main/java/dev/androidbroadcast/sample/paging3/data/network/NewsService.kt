package dev.androidbroadcast.sample.paging3.data.network

import androidx.annotation.IntRange
import dev.androidbroadcast.sample.paging3.data.network.model.ArticlesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

interface NewsService {

    /**
     * @param query Keywords or phrases to search for in the article title and body
     * @param pageSize The number of results to return per page. Default: 100. Maximum: 100.
     * @param page Use this to page through the results. Default: 1
     * @param queryInBody Keywords or phrases to search for in the article title only
     * @param sources A comma-seperated string of identifiers (maximum 20) for the news sources or blogs you want headlines from.
     * @param domains A comma-seperated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com) to restrict the search to.
     * @param excludeDomains A comma-seperated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com) to remove from the results.
     * @param from A date and optional time for the oldest article allowed. This should be in ISO 8601 format (e.g. 2021-03-11 or 2021-03-11T11:06:13)
     * @param to A date and optional time for the newest article allowed. This should be in ISO 8601 format (e.g. 2021-03-11 or 2021-03-11T11:06:13)
     * @param to The 2-letter ISO-639-1 code of the language you want to get headlines for.
     *
     * [Rest API Documentation](https://newsapi.org/docs/endpoints/everything)
     */
    @GET("/v2/everything")
    suspend fun everything(
        @Query("q") query: String? = null,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("pageSize") @IntRange(from = 1, to = MAX_PAGE_SIZE.toLong()) pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("qInTitle") queryInBody: String? = null,
        @Query("sources") sources: String? = null,
        @Query("domains") domains: String? = null,
        @Query("excludeDomains") excludeDomains: String? = null,
        @Query("from") from: Date? = null,
        @Query("to") to: Date? = null,
        @Query("language") language: Language? = null,
        @Query("sortBy") sortBy: SortBy? = null,
    ): Response<ArticlesResponseDto>

    enum class Language {
        ar,
        de,
        en,
        es,
        fr,
        he,
        it,
        nl,
        no,
        pt,
        ru,
        se,
        ud,
        zh,
    }

    enum class SortBy {
        /**
         * articles more closely related to query come first.
         */
        relevancy,

        /**
         * articles from popular sources and publishers come first
         */
        popularity,

        /**
         * newest articles come first
         */
        publishedAt
    }

    companion object {

        const val DEFAULT_PAGE_SIZE = 20
        const val MAX_PAGE_SIZE = 20
    }
}