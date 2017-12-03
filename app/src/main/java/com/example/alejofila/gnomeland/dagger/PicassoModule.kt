package com.example.alejofila.gnomeland.dagger

import android.content.Context
import com.example.alejofila.gnomeland.dagger.scope.AppScope
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
 * This is responsible for the Picasso instance (uses a preconfigured OkHttpclient in order to
 * enable cache)
 */
@Module
class PicassoModule {
    @Provides
    @AppScope
    fun providesPicasso(context: Context, okHttpClient: OkHttpClient): Picasso {
        val picassoBuilder = Picasso.Builder(context)
        picassoBuilder.downloader(OkHttp3Downloader(okHttpClient))
        return picassoBuilder.build()
    }
}