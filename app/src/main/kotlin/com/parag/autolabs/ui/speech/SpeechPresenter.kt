package com.parag.autolabs.ui.speech

import android.util.Log
import com.parag.autolabs.schedulers.ThreadScheduler
import com.parag.autolabs.ui.base.BasePresenter
import javax.inject.Inject

class SpeechPresenter @Inject constructor(threadScheduler: ThreadScheduler) : BasePresenter<SpeechView>(threadScheduler) {

    fun loadWeather(speechResult: ArrayList<String>) {
        Log.e(this.javaClass.simpleName, "Speech -> $speechResult")

        val filteredSpeech = speechResult.filter { it.contains("weather")}

        if (filteredSpeech.isNotEmpty())
            view?.displaySpeech(filteredSpeech)
        else
            view?.displayNoSpeech()
    }
}