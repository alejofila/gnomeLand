package com.example.alejofila.gnomeland.data

import com.example.alejofila.gnomeland.features.gnomelist.GnomeUIModel
import com.example.alejofila.gnomeland.features.gnomelist.GnomeViewRepository
import io.reactivex.Single

/**
 * This is a repository implementation that currently uses a remoteStore to get the data
 * but could be easily changed to a localStore
 */
class GnomeViewRepositoryImpl(val remoteStore: RemoteGnomeStore) : GnomeViewRepository {
    override fun getAllGnomes(): Single<List<GnomeUIModel>> {
        return remoteStore.getAllGnomes().cache()
    }

}