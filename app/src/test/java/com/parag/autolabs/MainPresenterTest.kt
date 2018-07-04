package com.parag.autolabs

import com.parag.autolabs.interactor.SpeechInteractor
import com.parag.autolabs.models.WeatherResult
import com.parag.autolabs.schedulers.ThreadScheduler
import com.parag.autolabs.ui.main.MainPresenter
import com.parag.autolabs.ui.main.MainView
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var interactor: SpeechInteractor
    private lateinit var scheduler: ThreadScheduler
    private lateinit var view: MainView
    private lateinit var pojoResults: List<WeatherResult>

    @Before
    fun setup() {
        interactor = Mockito.mock(SpeechInteractor::class.java)
        view = Mockito.mock(MainView::class.java)
        scheduler = Mockito.mock(ThreadScheduler::class.java)
        presenter = MainPresenter(interactor, TestScheduler())
        presenter.attach(view)
        pojoResults = TestUtil.pojoResults()
    }

    @Test
    fun shouldDisplayPOJOResutls() {
        Mockito.`when`(interactor.manageSpeechResult(emptyList()))
                .thenReturn(Observable.just(pojoResults))
        presenter.loadWeather(emptyList())

        Mockito.verify(view, Mockito.times(1)).displaySpeech(pojoResults)
    }

    @Test
    fun shouldDisplayErrorViewIfTheresNoJSONRetrievedFromBackend() {
        Mockito.`when`(interactor.manageSpeechResult(emptyList()))
                .thenReturn(Observable.error(Exception()))

        presenter.loadWeather(emptyList())

        Mockito.verify(view, Mockito.times(1)).displayNoSpeech()
    }
}