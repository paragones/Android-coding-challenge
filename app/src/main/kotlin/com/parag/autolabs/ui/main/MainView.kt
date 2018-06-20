package com.parag.autolabs.ui.main

import com.parag.autolabs.models.WeatherResult

interface MainView {
    fun displaySpeech(weatherResult: List<WeatherResult>)
    fun displayNoSpeech()
}