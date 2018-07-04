package com.parag.autolabs.interactor

import com.parag.autolabs.models.WeatherResult
import io.reactivex.Observable
import java.util.ArrayList

interface SpeechInteractor {
    fun manageSpeechResult(speechResult: List<String>)  : Observable<List<WeatherResult>>
}