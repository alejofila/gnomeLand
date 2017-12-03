package com.example.alejofila.gnomeland.features.gnomelist

import com.example.alejofila.gnomeland.base.BasePresenter
import com.example.alejofila.gnomeland.features.ErrorModel

/**
 *
 */
interface GnomeListContract {

    interface View {
        fun showGnomes(gnomes: List<GnomeUIModel>)
        fun showGnomeSpecificDetails(gnome: GnomeUIModel)
        fun showErrorMessage(error: ErrorModel)
        fun filterByName(name: String)

    }

    interface Presenter : BasePresenter {

        fun showListOfGnomes()
        fun goToGnomeDetails(gnomeUIModel: GnomeUIModel)
    }


}


