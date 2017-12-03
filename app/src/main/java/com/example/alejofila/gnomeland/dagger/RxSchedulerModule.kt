package com.example.alejofila.gnomeland.dagger

import com.example.alejofila.gnomeland.common.AndroidSchedulerProvider
import com.example.alejofila.gnomeland.common.SchedulerProvider
import com.example.alejofila.gnomeland.dagger.scope.AppScope
import dagger.Module
import dagger.Provides

/**
 * RxScheduler Provider for the whole App
 */
@Module
class RxSchedulerModule {

    @Provides
    @AppScope
    fun providesSchedulerProvider(): SchedulerProvider{
        return AndroidSchedulerProvider()
    }
}