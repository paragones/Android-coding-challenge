package com.parag.autolabs

import com.parag.autolabs.models.WeatherMainJson
import com.parag.autolabs.models.WeatherResult
import com.parag.autolabs.models.WeatherSystemJson


object TestUtil {

    fun pojoResults(): List<WeatherResult> {
        val list = arrayListOf<WeatherResult>()
        list.add(WeatherResult("", emptyList(), WeatherMainJson(""), WeatherSystemJson(0L,0L)))
        return list
    }
}