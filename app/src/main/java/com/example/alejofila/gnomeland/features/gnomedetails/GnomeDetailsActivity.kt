package com.example.alejofila.gnomeland.features.gnomedetails

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.alejofila.gnomeland.GnomeLandApp
import com.example.alejofila.gnomeland.R
import com.example.alejofila.gnomeland.dagger.DaggerGnomeDetailsComponent
import com.example.alejofila.gnomeland.dagger.GnomeDetailsComponent
import com.example.alejofila.gnomeland.dagger.GnomeDetailsModule
import com.example.alejofila.gnomeland.dagger.GnomeListModule
import com.example.alejofila.gnomeland.features.gnomelist.GnomeUIModel
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_gnome_details.*
import javax.inject.Inject

class GnomeDetailsActivity : AppCompatActivity(), GnomeDetailsContract.View {


    @Inject lateinit var picasso: Picasso
    @Inject lateinit var presenter: GnomeDetailsContract.Presenter
    lateinit var gnomeUIModel: GnomeUIModel
    lateinit var component: GnomeDetailsComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.gnome_details_activity_title)
        setContentView(R.layout.activity_gnome_details)
        gnomeUIModel = getGnomeDetails()
        setUpDaggerComponent()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        presenter.stop()
        super.onStop()
    }

    override fun showGnomeName(name: String) {
        detailsGnomeName.text = name
    }

    override fun showGnomeImage(url: String) {
        picasso.load(url)
                .fit()
                .placeholder(R.drawable.ic_help_outline)
                .error(R.drawable.ic_help_outline)
                .into(detailsGnomeImage)
    }

    override fun showGnomeProfessions(professions: String) {
        detailsProfessions.text = professions
    }

    override fun showGnomeFriends(friends: String) {
        detailsFriends.text = friends
    }

    override fun showGnomeAge(age: String) {
        detailsGnomeAge.text = age
    }

    override fun showGnomeHairColor(color: String) {
        detailsHairColor.text = color
    }

    private fun setUpDaggerComponent() {

        component = DaggerGnomeDetailsComponent.builder()
                .appComponent(GnomeLandApp.component)
                .gnomeDetailsModule(GnomeDetailsModule(this))
                .build()
        component.inject(this)
    }

    override fun showNoFriends() {
        detailsFriends.text = getString(R.string.gnome_details_no_friends, gnomeUIModel.name)
    }

    override fun showNoProfessions() {
        detailsFriends.text = getString(R.string.gnome_details_no_professions, gnomeUIModel.name)

    }

    private fun getGnomeDetails(): GnomeUIModel {
        return intent.getParcelableExtra(GNOME_DETAILS_KEY)
    }

    companion object {
        const val GNOME_DETAILS_KEY = "com.example.alejofila.gnomeland.GNOME_DETAILS"
        fun getIntent(context: Context, gnomeUIModel: GnomeUIModel): Intent {
            val intent = Intent(context, GnomeDetailsActivity::class.java)
            return intent.putExtra(GNOME_DETAILS_KEY, gnomeUIModel)
        }
    }
}
