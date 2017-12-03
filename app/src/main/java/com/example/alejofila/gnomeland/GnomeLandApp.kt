package com.example.alejofila.gnomeland

import android.app.Application
import com.example.alejofila.gnomeland.dagger.AppComponent
import com.example.alejofila.gnomeland.dagger.AppModule
import com.example.alejofila.gnomeland.dagger.DaggerAppComponent


/**
 * Application class that holds the Dagger entry point to the dependencyGraph (AppComponent)
 */
class GnomeLandApp : Application() {
    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        setUpDaggerAppComponent()
    }

    private fun setUpDaggerAppComponent() {
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

    }


}