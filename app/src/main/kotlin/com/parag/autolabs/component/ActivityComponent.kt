package com.parag.autolabs.component

import com.parag.autolabs.modules.*
import com.parag.autolabs.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class,
        ThreadModule::class,
        NetworkModule::class,
        InteractorModule::class,
        RepositoryModule::class,
        RestModule::class,
        DataMapperModule::class))
interface ActivityComponent {
    fun inject(activity: MainActivity)
}