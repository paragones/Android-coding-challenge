package com.parag.autolabs.component

import com.parag.autolabs.modules.*
import com.parag.autolabs.ui.speech.SpeechActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class,
        ThreadModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DataMapperModule::class))
interface ActivityComponent {
    fun inject(activity: SpeechActivity)
}