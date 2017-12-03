package com.example.alejofila.gnomeland.dagger

import com.example.alejofila.gnomeland.dagger.scope.ActivityScope
import com.example.alejofila.gnomeland.features.gnomedetails.GnomeDetailsActivity
import dagger.Component

/**
 * Specific component for  GnomeDetails
 */
@ActivityScope
@Component(modules = arrayOf(GnomeDetailsModule::class), dependencies = arrayOf(AppComponent::class))
interface GnomeDetailsComponent {
    fun inject(activity: GnomeDetailsActivity)
}