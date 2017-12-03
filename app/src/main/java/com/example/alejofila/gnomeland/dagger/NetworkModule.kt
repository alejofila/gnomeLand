package com.example.alejofila.gnomeland.dagger

import android.content.Context
import com.example.alejofila.gnomeland.dagger.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File


/**
 * Responsable of configuring the HTTP Transport layer
 */
@Module
class NetworkModule {


    @Provides
    @AppScope
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    @AppScope
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000) //10MB Cahe
    }

    @Provides
    @AppScope
    fun cacheFile(context: Context): File {
        return File(context.getCacheDir(), "okhttp_cache")
    }

    @Provides
    @AppScope
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                
                .cache(cache)
                .build()
    }

}