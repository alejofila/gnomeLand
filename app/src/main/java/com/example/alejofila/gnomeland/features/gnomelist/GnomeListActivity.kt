package com.example.alejofila.gnomeland.features.gnomelist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.alejofila.gnomeland.GnomeLandApp
import com.example.alejofila.gnomeland.R
import com.example.alejofila.gnomeland.dagger.GnomeListComponent
import com.example.alejofila.gnomeland.dagger.GnomeListModule
import kotlinx.android.synthetic.main.activity_gnome_list.*
import javax.inject.Inject

import android.app.SearchManager
import android.content.Context
import android.os.Parcelable
import android.os.PersistableBundle
import android.support.v7.widget.SearchView
import android.widget.Toast
import com.example.alejofila.gnomeland.dagger.DaggerGnomeListComponent
import com.example.alejofila.gnomeland.features.ErrorModel
import com.example.alejofila.gnomeland.features.gnomedetails.GnomeDetailsActivity
import io.reactivex.disposables.Disposable


class GnomeListActivity : AppCompatActivity(), GnomeListContract.View {

    var listState: Parcelable? = null
    lateinit var component: GnomeListComponent
    lateinit var adapter: GnomeRecyclerViewAdapter
    lateinit var searchView: SearchView
    private var recyclerClickEventDisposable: Disposable? = null

    @Inject lateinit var presenter: GnomeListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gnome_list)
        setUpDaggerComponent()
        adapter = GnomeRecyclerViewAdapter()
        gnomeRecyclerView.adapter = adapter
        listState = savedInstanceState?.getParcelable("ListState")



    }


    override fun onStart() {
        super.onStart()
        presenter.start()

    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("ListState", gnomeRecyclerView.layoutManager.onSaveInstanceState())
    }

    private fun setUpDaggerComponent() {
        component = DaggerGnomeListComponent.builder()
                .appComponent(GnomeLandApp.component)
                .gnomeListModule(GnomeListModule(this))
                .build()
        component.inject(this)
    }

    override fun showGnomes(gnomes: List<GnomeUIModel>) {
        adapter.swapData(gnomes)
        if (recyclerClickEventDisposable == null) {
            recyclerClickEventDisposable = adapter.clickEvent
                    .subscribe { gnome ->
                        presenter.goToGnomeDetails(gnome)
                    }
        }
        gnomeRecyclerView.layoutManager.onRestoreInstanceState(listState);
    }


    override fun showGnomeSpecificDetails(gnome: GnomeUIModel) {
        startActivity(GnomeDetailsActivity.getIntent(this, gnome))
    }

    override fun showErrorMessage(error: ErrorModel) {
        Toast.makeText(this, getString(error.message()), Toast.LENGTH_SHORT)
                .show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.queryHint = getString(R.string.query_hint)
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                filterByName(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                filterByName(query)
                return false
            }
        })
        return true
    }

    override fun filterByName(name: String) {
        adapter.filter.filter(name)
    }


    override fun onBackPressed() {

        if (!searchView.isIconified) {
            searchView.isIconified = true
            return;
        }
        super.onBackPressed()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        recyclerClickEventDisposable?.dispose()
    }
}
