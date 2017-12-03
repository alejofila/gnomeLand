package com.example.alejofila.gnomeland.dagger

import android.content.Context
import com.example.alejofila.gnomeland.dagger.scope.AppScope
import dagger.Module
import dagger.Provides

/**
 *
 */
@Module
class AppModule(private val context: Context) {

    @AppScope
    @Provides
    fun providesContext(): Context {
        return context
    }


}