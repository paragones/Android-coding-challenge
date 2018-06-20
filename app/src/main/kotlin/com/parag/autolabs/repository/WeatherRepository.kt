package com.parag.autolabs.repository

import com.parag.autolabs.models.WeatherResult
import io.reactivex.Observable

interface WeatherRepository {
    fun getWeatherFromCity(cityName: String): Observable<WeatherResult>
}