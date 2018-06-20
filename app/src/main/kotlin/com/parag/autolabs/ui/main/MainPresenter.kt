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


class MainPresenter @Inject constructor(private val context: Context, private val interactor: SpeechInteractor, threadScheduler: ThreadScheduler) : BasePresenter<MainView>(threadScheduler) {
    private val spaceDelimiter = "\\s+"

    fun loadWeather(speechResult: ArrayList<String>) {
        interactor.manageSpeechResult(speechResult)
                .compose(allocateSchedule())
                .subscribe({

                    if (it.isEmpty()) view?.displayNoSpeech()
                    else view?.displaySpeech(it)
                    Log.e(this.javaClass.simpleName, "it $it")

//                    for (weather in it) { Log.e(this.javaClass.simpleName, "weather $weather") }
                }, {
                    view?.displayNoSpeech()
                    Log.e(this.javaClass.simpleName, "error ", it)
                })
    }
}