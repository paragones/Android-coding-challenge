package com.parag.autolabs.interactor

import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import com.parag.autolabs.models.WeatherResult
import com.parag.autolabs.repository.WeatherRepository
import io.reactivex.Observable
import java.util.*

class SpeechInteractorImpl(private val context: Context, private val repository: WeatherRepository): SpeechInteractor {

    override fun manageSpeechResult(speechResult: List<String>) : Observable<List<WeatherResult>> {
        val filteredSpeech = speechResult.filter { it.contains("weather") }
        val cities = ArrayList<String>()
        val spaceDelimiter = " "


        filteredSpeech.forEach {
            val words = it.split(spaceDelimiter)
            words.forEach {
                if (it[0].isUpperCase()) cities.add(it)
            }
        }

        return when {
            filteredSpeech.isNotEmpty() && cities.isEmpty() -> getWeatherFromCurrentCity()
            cities.isNotEmpty() -> getWeatherFromCities(cities)
            else -> Observable.just(emptyList())
        }
    }

    private fun getWeatherFromCities(cities: List<String>): Observable<List<WeatherResult>> {
        return Observable.just(cities)
                .flatMap { Observable.fromIterable(it) }
                .flatMap { repository.getWeatherFromCity(it) }
                .toList()
                .toObservable()
    }

    private fun getWeatherFromCurrentCity(): Observable<List<WeatherResult>> {
        val cities = arrayListOf<String>()
        cities.add(getCurrentCity())
        return getWeatherFromCities(cities)
    }


    private fun getCurrentCity(): String {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val longitude = location.longitude
        val latitude = location.latitude

        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        return addresses[0].locality
    }
}