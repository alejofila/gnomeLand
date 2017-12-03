package com.example.alejofila.gnomeland.dagger

import com.example.alejofila.gnomeland.common.SchedulerProvider
import com.example.alejofila.gnomeland.dagger.scope.ActivityScope
import com.example.alejofila.gnomeland.features.gnomedetails.GnomeDetailsActivity
import com.example.alejofila.gnomeland.features.gnomedetails.GnomeDetailsContract
import com.example.alejofila.gnomeland.features.gnomedetails.GnomeDetailsPresenter
import com.example.alejofila.gnomeland.features.gnomelist.GnomeListContract
import com.example.alejofila.gnomeland.features.gnomelist.GnomeListPresenter
import com.example.alejofila.gnomeland.features.gnomelist.GnomeUIModel
import com.example.alejofila.gnomeland.features.gnomelist.GnomeViewRepository
import dagger.Module
import dagger.Provides

/**
 *
 */
@Module
class GnomeDetailsModule(val activity: GnomeDetailsActivity) {

    @Provides
    @ActivityScope
    fun providesGnomeListPresenter(gnomeUIModel: GnomeUIModel, view: GnomeDetailsContract.View,
                                   schedulerProvider: SchedulerProvider):
            GnomeDetailsContract.Presenter {
        return GnomeDetailsPresenter(gnomeUIModel, view, schedulerProvider)
    }

    @Provides
    @ActivityScope
    fun providesGnomeListView(): GnomeDetailsContract.View {
        return activity
    }

    @Provides
    @ActivityScope
    fun providesGnomeUIModel(): GnomeUIModel {
        return activity.gnomeUIModel
    }
}