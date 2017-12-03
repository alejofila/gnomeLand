package com.example.alejofila.gnomeland.datamapper

import com.example.alejofila.gnomeland.features.gnomelist.GnomeUIModel
import com.example.alejofila.gnomeland.network.Gnome

/**
 * This file holds functions to map a Gnome to different representations in different layers
 */
fun fromApiResponseToUIModel(remoteGnome: Gnome): GnomeUIModel{
    return GnomeUIModel(remoteGnome.id,
            remoteGnome.name,
            remoteGnome.age,
            remoteGnome.hairColor,
            remoteGnome.friends,
            remoteGnome.image,
            remoteGnome.professions)
}