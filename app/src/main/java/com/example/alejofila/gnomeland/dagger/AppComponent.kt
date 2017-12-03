package com.example.alejofila.gnomeland.dagger

import android.content.Context
import com.example.alejofila.gnomeland.GnomeLandApp
import com.example.alejofila.gnomeland.common.SchedulerProvider
import com.example.alejofila.gnomeland.dagger.scope.AppScope
import com.example.alejofila.gnomeland.data.RemoteGnomeStore
import com.example.alejofila.gnomeland.features.gnomelist.GnomeViewRepository
import com.example.alejofila.gnomeland.network.GnomeService
import com.squareup.picasso.Picasso
import dagger.Component

/**
 * Main Dagger component hosting "singleton" dependencies
 */
@AppScope
@Component(modules = arrayOf(AppModule::class, GnomeServiceModule::class, RxSchedulerModule::class, PicassoModule::class))
interface AppComponent {
    fun inject(app: GnomeLandApp)
    fun context(): Context
    fun schedulerProvider(): SchedulerProvider
    fun gnomeService(): GnomeService
    fun remoteGnomeStore(): RemoteGnomeStore
    fun picasso(): Picasso
    fun gnomeViewRepository(): GnomeViewRepository
}