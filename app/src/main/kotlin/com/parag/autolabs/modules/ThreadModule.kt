package com.parag.autolabs.modules

import com.parag.autolabs.schedulers.ThreadScheduler
import com.parag.autolabs.schedulers.ThreadSchedulerImpl
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class ThreadModule {
    private val subscribeOnThread = Schedulers.io()
    private val observeOnThread = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    fun provideThreadAllocation(): ThreadScheduler = ThreadSchedulerImpl(subscribeOnThread, observeOnThread)
}