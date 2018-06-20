package com.parag.autolabs.modules

import android.content.Context
import com.parag.autolabs.interactor.SpeechInteractor
import com.parag.autolabs.interactor.SpeechInteractorImpl
import com.parag.autolabs.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideSpeechInteractor(context: Context, repository: WeatherRepository): SpeechInteractor = SpeechInteractorImpl(context, repository)
}