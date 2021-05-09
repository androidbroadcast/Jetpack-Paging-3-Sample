package dev.androidbroadcast.sample.paging3.di

import android.app.Application
import androidx.room.Room
import coil.ImageLoader
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dev.androidbroadcast.sample.paging3.Dispatchers
import dev.androidbroadcast.sample.paging3.BuildConfig
import dev.androidbroadcast.sample.paging3.data.network.AuthInterceptor
import dev.androidbroadcast.sample.paging3.data.network.NewsService
import dev.androidbroadcast.sample.paging3.data.storage.NewsDatabase
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(application, NewsDatabase::class.java, "news")
            .build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json(Json.Default) {
            ignoreUnknownKeys = true
        }
    }

    @Singleton
    @Provides
    fun provideNewsService(json: Json): NewsService {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(BuildConfig.NEWS_API_KEY))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create(NewsService::class.java)
    }

    @Singleton
    @Provides
    fun provideDispatchers(): Dispatchers {
        return Dispatchers.Default
    }

    @Singleton
    @Provides
    fun provideImageLoader(application: Application): ImageLoader {
        return ImageLoader(application)
    }
}