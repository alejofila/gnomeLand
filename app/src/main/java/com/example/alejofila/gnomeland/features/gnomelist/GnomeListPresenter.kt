package com.example.alejofila.gnomeland.features.gnomelist

import com.example.alejofila.gnomeland.common.SchedulerProvider
import com.example.alejofila.gnomeland.features.ErrorModelImpl
import io.reactivex.disposables.CompositeDisposable

/**
 *
 */
class GnomeListPresenter(val view: GnomeListContract.View,
                         private val repository: GnomeViewRepository,
                         override val schedulerProvider: SchedulerProvider) : GnomeListContract.Presenter {

    val disposableBag: CompositeDisposable = CompositeDisposable()
    override fun showListOfGnomes() {
        disposableBag.add(repository.getAllGnomes()
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe({ list ->
                    if (list.isNotEmpty()) {
                        view.showGnomes(list)
                    }
                },
                        { onError: Throwable ->
                            view.showErrorMessage(ErrorModelImpl.NO_INTERNET_CONNECTION)
                        }
                )


        )
    }

    override fun goToGnomeDetails(gnomeUIModel: GnomeUIModel) {
        view.showGnomeSpecificDetails(gnomeUIModel)
    }

    override fun start() {
            showListOfGnomes()
    }

    override fun stop() {
        disposableBag.clear()
    }

}