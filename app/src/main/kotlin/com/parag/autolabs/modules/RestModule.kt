package com.parag.autolabs.modules

import com.parag.autolabs.rest.WeatherRest
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RestModule {

    @Provides
    @Singleton
    fun provideWeatherRest(retrofit: Retrofit) : WeatherRest = retrofit.create(WeatherRest::class.java)
}