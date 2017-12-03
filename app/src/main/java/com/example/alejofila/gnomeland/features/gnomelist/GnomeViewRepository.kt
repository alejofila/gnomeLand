package com.example.alejofila.gnomeland.features.gnomelist

import io.reactivex.Single

/**
 *
 */
interface GnomeViewRepository{
    fun getAllGnomes() : Single<List<GnomeUIModel>>
}