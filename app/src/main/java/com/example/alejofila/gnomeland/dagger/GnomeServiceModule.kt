package com.example.alejofila.gnomeland.dagger

import com.example.alejofila.gnomeland.BuildConfig
import com.example.alejofila.gnomeland.dagger.scope.ActivityScope
import com.example.alejofila.gnomeland.dagger.scope.AppScope
import com.example.alejofila.gnomeland.data.GnomeViewRepositoryImpl
import com.example.alejofila.gnomeland.data.RemoteGnomeStore
import com.example.alejofila.gnomeland.features.gnomelist.GnomeViewRepository
import com.example.alejofila.gnomeland.network.GnomeService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 */
@Module(includes = arrayOf(NetworkModule::class))
class GnomeServiceModule {


    @AppScope
    @Provides
    fun providesRemoteGnomeStore(gnomeService: GnomeService): RemoteGnomeStore {
        return RemoteGnomeStore(gnomeService)
    }

    @AppScope
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @AppScope
    @Provides
    fun providesGnomeService(retrofit: Retrofit): GnomeService {
        return retrofit.create(GnomeService::class.java)
    }

    @Provides
    @AppScope
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
    }

    @Provides
    @AppScope
    fun providesGnomeViewRepository(remoteGnomeStore: RemoteGnomeStore): GnomeViewRepository {
        return GnomeViewRepositoryImpl(remoteGnomeStore)
    }
}