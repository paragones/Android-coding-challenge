package com.parag.autolabs.interactor

import com.parag.autolabs.models.WeatherResult
import io.reactivex.Observable
import java.util.ArrayList

interface SpeechInteractor {
//    fun getCityWeather(cityName: String): Observable<WeatherResult>
    fun manageSpeechResult(speechResult: ArrayList<String>)  : Observable<List<WeatherResult>>
}