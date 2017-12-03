package com.example.alejofila.gnomeland.dagger

import com.example.alejofila.gnomeland.common.SchedulerProvider
import com.example.alejofila.gnomeland.dagger.scope.ActivityScope
import com.example.alejofila.gnomeland.data.GnomeViewRepositoryImpl
import com.example.alejofila.gnomeland.data.RemoteGnomeStore
import com.example.alejofila.gnomeland.features.gnomelist.*

import dagger.Module
import dagger.Provides

/**
 *
 */
@Module
class GnomeListModule(val activity: GnomeListActivity) {
    @Provides
    @ActivityScope
    fun providesGnomeListPresenter(view: GnomeListContract.View,
                                   repository: GnomeViewRepository,
                                   schedulerProvider: SchedulerProvider): GnomeListContract.Presenter {
        return GnomeListPresenter(view, repository, schedulerProvider)
    }

    @Provides
    @ActivityScope
    fun providesGnomeListView(): GnomeListContract.View {
        return activity
    }


}