package com.parag.autolabs.rest

import com.parag.autolabs.models.WeatherResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRest {

    @GET("weather?units=metric")
    fun getWeatherResults(@Query("q") cityName: String, @Query("APPID") key: String) : Observable<WeatherResult>
}