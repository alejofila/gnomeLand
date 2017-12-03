package com.example.alejofila.gnomeland.features.gnomelist

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
 */
/*
This is the model representing a Gnome in the UI layer, it may be pretty similar to the
data model, but is better to have different data classes for each layer because
they can diverge in the future
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class GnomeUIModel(
        val id: Long = 0,
        val name: String = "Andrew",
        val age: Int = 0,
        val hairColor: String = "black",
        val friends: List<String> = emptyList(),
        val image: String = "",
        val professions: List<String> = emptyList()
): Parcelable

