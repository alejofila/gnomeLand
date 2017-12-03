package com.example.alejofila.gnomeland.features

import com.example.alejofila.gnomeland.TestSchedulerProvider
import com.example.alejofila.gnomeland.common.SchedulerProvider
import com.example.alejofila.gnomeland.features.gnomelist.*
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 *
 */
class GnomeListPresenterTest {

    @Mock lateinit var mockedView: GnomeListContract.View
    @Mock lateinit var gnomeViewRepository: GnomeViewRepository
    @Mock lateinit var errorModel : ErrorModel
    lateinit var testSchedulerProvider: SchedulerProvider
    lateinit var rxTestScheduler: TestScheduler

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        rxTestScheduler = TestScheduler()
        testSchedulerProvider = TestSchedulerProvider(rxTestScheduler)
    }

    @Test
    fun testLoadingViewWithAValidListOfGnomes() {
        val listOfGnomes = listOf(GnomeUIModel(), GnomeUIModel())

        Mockito.`when`(gnomeViewRepository.getAllGnomes()).thenReturn(Single.just(listOfGnomes))

        val presenter = GnomeListPresenter(mockedView, gnomeViewRepository, testSchedulerProvider)

        presenter.start()
        rxTestScheduler.triggerActions()
        Mockito.verify(mockedView, atMost(1)).showGnomes(listOfGnomes)

    }

    @Test
    fun testLoadingViewWithAListOfZeroGnomes() {
        val listOfGnomes = emptyList<GnomeUIModel>()
        Mockito.`when`(gnomeViewRepository.getAllGnomes()).thenReturn(Single.just(listOfGnomes))

        val presenter = GnomeListPresenter(mockedView, gnomeViewRepository, testSchedulerProvider)
        presenter.start()
        rxTestScheduler.triggerActions()
        Mockito.verify(mockedView, never()).showGnomes(listOfGnomes)

    }


    @Test
    fun testErrorOnRequest() {
        Mockito.`when`(gnomeViewRepository.getAllGnomes()).thenReturn(Single.error(Throwable()))

        val presenter = GnomeListPresenter(mockedView, gnomeViewRepository, testSchedulerProvider)
        presenter.start()
        rxTestScheduler.triggerActions()
        Mockito.verify(mockedView, atMost(1)).showErrorMessage(errorModel)

    }
}