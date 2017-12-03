package com.example.alejofila.gnomeland.dagger

import com.example.alejofila.gnomeland.dagger.scope.ActivityScope
import com.example.alejofila.gnomeland.features.gnomedetails.GnomeDetailsActivity
import com.example.alejofila.gnomeland.features.gnomelist.GnomeListActivity
import com.example.alejofila.gnomeland.features.gnomelist.GnomeListContract
import com.example.alejofila.gnomeland.network.GnomeService
import dagger.Component

/**
 * Specific component for GnomeList
 */
@ActivityScope
@Component(modules = arrayOf(GnomeListModule::class), dependencies = arrayOf(AppComponent::class))
interface GnomeListComponent {
    fun inject(activity: GnomeListActivity)
    fun gnomeListPresenter(): GnomeListContract.Presenter
}