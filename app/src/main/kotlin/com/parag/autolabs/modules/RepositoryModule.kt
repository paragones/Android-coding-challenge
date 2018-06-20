package com.parag.autolabs.modules

import com.parag.autolabs.repository.WeatherRepository
import com.parag.autolabs.repository.WeatherRepositoryImpl
import com.parag.autolabs.rest.WeatherRest
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherRest: WeatherRest): WeatherRepository = WeatherRepositoryImpl(weatherRest)
}