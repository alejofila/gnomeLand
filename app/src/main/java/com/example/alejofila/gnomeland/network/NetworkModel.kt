package com.example.alejofila.gnomeland.network

import com.google.gson.annotations.SerializedName

/**
 *
 */
data class GnomesResponse(@SerializedName("Brastlewark") val gnomes: List<Gnome>)

data class Gnome(
        val id: Long,
        val name: String,
        val age: Int,
        @SerializedName("hair_color") val hairColor: String,
        val friends: List<String>,
        @SerializedName("thumbnail") val image: String,
        val professions: List<String>)