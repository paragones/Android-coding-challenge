package com.parag.autolabs.modules

import android.content.Context
import com.parag.autolabs.services.ImageLoader
import com.parag.autolabs.services.PicasoImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Provides
    @Singleton
    fun provideImageLoader(context: Context): ImageLoader = PicasoImageLoader(context)
}