package com.parag.autolabs.modules

import com.parag.autolabs.services.DataMapper
import com.parag.autolabs.services.DataMapperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataMapperModule {

    @Provides
    @Singleton
    fun provideDataMapper(): DataMapper = DataMapperImpl()
}