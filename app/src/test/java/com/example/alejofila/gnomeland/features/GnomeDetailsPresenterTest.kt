package com.example.alejofila.gnomeland.features

import com.example.alejofila.gnomeland.TestSchedulerProvider
import com.example.alejofila.gnomeland.common.SchedulerProvider
import com.example.alejofila.gnomeland.features.gnomedetails.GnomeDetailsContract
import com.example.alejofila.gnomeland.features.gnomedetails.GnomeDetailsPresenter
import com.example.alejofila.gnomeland.features.gnomelist.GnomeUIModel
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

/**
 * Created by alejofila on 3/12/17.
 */
class GnomeDetailsPresenterTest {

    @Mock lateinit var mockedView: GnomeDetailsContract.View
    lateinit var testSchedulerProvider: SchedulerProvider
    lateinit var rxTestScheduler: TestScheduler


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        rxTestScheduler = TestScheduler()
        testSchedulerProvider = TestSchedulerProvider(rxTestScheduler)
    }

    @Test
    fun testAValidGnomeWithNoFriends() {
        val gnomeUIModel = GnomeUIModel()

        val presenter = GnomeDetailsPresenter(gnomeUIModel, mockedView, testSchedulerProvider)

        presenter.start()
        rxTestScheduler.triggerActions()
        Mockito.verify(mockedView, times(1)).showNoFriends()
    }

    @Test
    fun testAValidGnomeWithNoProfessions() {
        val lazyGnome = GnomeUIModel()

        val presenter = GnomeDetailsPresenter(lazyGnome, mockedView, testSchedulerProvider)

        presenter.start()
        rxTestScheduler.triggerActions()
        Mockito.verify(mockedView, times(1)).showNoProfessions()
    }

    @Test
    fun testAValidGnomeWithFriendsAndProfessions() {
        val lazyGnome = GnomeUIModel(professions = listOf("Soldier", "Priest"), friends = listOf("Andrew M", "Filipe"))

        val presenter = GnomeDetailsPresenter(lazyGnome, mockedView, testSchedulerProvider)

        presenter.start()
        rxTestScheduler.triggerActions()
        Mockito.verify(mockedView, times(1)).showGnomeHairColor(ArgumentMatchers.anyString())
        Mockito.verify(mockedView, times(1)).showGnomeName(ArgumentMatchers.anyString())
        Mockito.verify(mockedView, times(1)).showGnomeImage(ArgumentMatchers.anyString())
        Mockito.verify(mockedView, times(1)).showGnomeProfessions(ArgumentMatchers.anyString())
        Mockito.verify(mockedView, times(1)).showGnomeFriends(ArgumentMatchers.anyString())
        Mockito.verify(mockedView, times(1)).showGnomeAge(ArgumentMatchers.anyString())

    }

}