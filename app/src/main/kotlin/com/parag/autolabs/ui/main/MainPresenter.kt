package com.parag.autolabs.ui.main

import android.content.Context
import android.util.Log
import com.parag.autolabs.interactor.SpeechInteractor
import com.parag.autolabs.models.WeatherResult
import com.parag.autolabs.schedulers.ThreadScheduler
import com.parag.autolabs.ui.base.BasePresenter
import io.reactivex.ObservableSource
import java.util.*
import javax.inject.Inject


class MainPresenter @Inject constructor(private val interactor: SpeechInteractor, threadScheduler: ThreadScheduler) : BasePresenter<MainView>(threadScheduler) {
    fun loadWeather(speechResult: List<String>) {
        interactor.manageSpeechResult(speechResult)
                .compose(allocateSchedule())
                .subscribe({
                    if (it.isEmpty()) view?.displayNoSpeech()
                    else view?.displaySpeech(it)
                }, {
                    view?.displayNoSpeech()
                })
    }
}