package com.parag.autolabs.repository

import android.util.Log
import com.parag.autolabs.BuildConfig.WEATHER_APP_ID
import com.parag.autolabs.models.WeatherResult
import com.parag.autolabs.rest.WeatherRest
import io.reactivex.Observable

class WeatherRepositoryImpl(val weatherRest: WeatherRest): WeatherRepository {

    override fun getWeatherFromCity(cityName: String): Observable<WeatherResult> {
        return weatherRest.getWeatherResults(cityName, WEATHER_APP_ID)
    }
}