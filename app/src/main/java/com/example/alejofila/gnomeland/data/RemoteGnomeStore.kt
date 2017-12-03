package com.example.alejofila.gnomeland.data

import com.example.alejofila.gnomeland.datamapper.fromApiResponseToUIModel
import com.example.alejofila.gnomeland.features.gnomelist.GnomeUIModel
import com.example.alejofila.gnomeland.network.GnomeService
import com.example.alejofila.gnomeland.network.GnomesResponse
import io.reactivex.Single

/**
 * This is a store that retrieves gnomes from an remote endpoint
 */
class RemoteGnomeStore(private val gnomeService: GnomeService) {

    fun getAllGnomes(): Single<List<GnomeUIModel>> {
        return gnomeService.getGnomes().map {
            it.gnomes.map { gnome -> fromApiResponseToUIModel(gnome) }
        }
    }
}