package com.parag.autolabs.interactor

import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import android.util.Log
import com.parag.autolabs.models.WeatherResult
import com.parag.autolabs.repository.WeatherRepository
import io.reactivex.Observable
import java.util.*

class SpeechInteractorImpl(private val context: Context, val repository: WeatherRepository): SpeechInteractor {
    private val spaceDelimiter = "\\s+"
    val cities = ArrayList<String>()


    override fun manageSpeechResult(speechResult: ArrayList<String>) : Observable<List<WeatherResult>> {
        val filteredSpeech = speechResult.filter { it.contains("weather") }

        Log.e(this.javaClass.simpleName, "filteredSpeech $filteredSpeech")

        filteredSpeech.map {
            val words = it.split(spaceDelimiter)
            words.map {
                if (it[0].isUpperCase()) cities.add(it)
            }
        }

        Log.e(this.javaClass.simpleName, "cities $cities")
        Log.e(this.javaClass.simpleName, "filteredSpeech.isNotEmpty() ${filteredSpeech.isNotEmpty()}")
        Log.e(this.javaClass.simpleName, "cities.isEmpty() ${cities.isEmpty()}")
        Log.e(this.javaClass.simpleName, "filteredSpeech.isNotEmpty() && cities.isEmpty() ${filteredSpeech.isNotEmpty() && cities.isEmpty()}")

        return when {
            filteredSpeech.isNotEmpty() && cities.isEmpty() -> getWeatherFromCurrentCity()
//            cities.isNotEmpty() -> isCitySpecified = false
            else -> Observable.just(emptyList())
        }
    }

//    override fun getCityWeather(cityName: String): Observable<List<WeatherResult>> {
//        repository.getCityWeather("")
//                .repeat(cities.size.toLong())
//                .subscribe {
//
//                })
//    }

    private fun getWeatherFromCities() {

    }

    private fun getWeatherFromCurrentCity(): Observable<List<WeatherResult>> {
        Log.e(this.javaClass.simpleName, "getWeatherFromCurrentCity")

        val cities = arrayListOf<String>()

        cities.add(getCurrentCity())

        Log.e(this.javaClass.simpleName, "cities $cities")

        return Observable.just(cities)
                .flatMap { Observable.fromIterable(it) }
                .flatMap { repository.getWeatherFromCity(it) }
                .toList()
                .toObservable()
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