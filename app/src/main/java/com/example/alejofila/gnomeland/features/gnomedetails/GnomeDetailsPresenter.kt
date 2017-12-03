package com.example.alejofila.gnomeland.features.gnomedetails

import com.example.alejofila.gnomeland.common.SchedulerProvider
import com.example.alejofila.gnomeland.features.gnomelist.GnomeUIModel

/**
 * Created by alejofila on 3/12/17.
 */
class GnomeDetailsPresenter(val gnomeUIModel: GnomeUIModel, val view: GnomeDetailsContract.View,
                            override val schedulerProvider: SchedulerProvider) : GnomeDetailsContract.Presenter {
    override fun start() {
        loadGnomeInfo()
    }

    override fun stop() {
    }

    override fun loadGnomeInfo() {
        setGnomeAge()
        setGnomeFriends()
        setGnomeImage()
        setGnomeProfessions()
        setGnomeName()
        setGnomeHairColor()

    }

    private fun setGnomeHairColor() {
        view.showGnomeHairColor(gnomeUIModel.hairColor)
    }

    private fun setGnomeName() {
        view.showGnomeName(gnomeUIModel.name)
    }

    private fun setGnomeProfessions() {
        if (gnomeUIModel.professions.isNotEmpty()) {
            view.showGnomeProfessions(gnomeUIModel.professions.toString())

        } else {
            view.showNoProfessions()
        }
    }

    private fun setGnomeImage() {
        view.showGnomeImage(gnomeUIModel.image)
    }

    private fun setGnomeFriends() {
        if (gnomeUIModel.friends.isNotEmpty()) {
            view.showGnomeFriends(gnomeUIModel.friends.toString())
        } else {
            view.showNoFriends()
        }
    }

    private fun setGnomeAge() {
        view.showGnomeAge(gnomeUIModel.age.toString())

    }

}