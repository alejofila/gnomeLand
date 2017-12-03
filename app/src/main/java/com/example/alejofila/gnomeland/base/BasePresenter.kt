package com.example.alejofila.gnomeland.base

import com.example.alejofila.gnomeland.common.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 *
 */
interface BasePresenter {
    val schedulerProvider: SchedulerProvider
    fun start()
    fun stop()
}