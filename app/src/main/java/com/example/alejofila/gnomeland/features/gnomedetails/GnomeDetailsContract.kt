package com.example.alejofila.gnomeland.features.gnomedetails

import com.example.alejofila.gnomeland.base.BasePresenter

/**
 *
 */
interface GnomeDetailsContract {
    interface View {
        fun showGnomeName(name: String)
        fun showGnomeImage(url: String)
        fun showGnomeProfessions(professions: String)
        fun showGnomeFriends(friends: String)
        fun showGnomeAge(age: String)
        fun showGnomeHairColor(color: String)
        fun showNoFriends()
        fun showNoProfessions()
    }

    interface Presenter : BasePresenter {
        fun loadGnomeInfo()
    }
}