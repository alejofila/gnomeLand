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
    //var alreadyShowed = false

    val disposableBag: CompositeDisposable = CompositeDisposable()
    override fun showListOfGnomes() {
        disposableBag.add(repository.getAllGnomes()
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .cache()

                .subscribe({ list ->
                    if (list.isNotEmpty()) {
                        view.showGnomes(list)
                        //alreadyShowed = true
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
        //if (!alreadyShowed)
        showListOfGnomes()
    }

    override fun stop() {
        disposableBag.clear()
    }

}