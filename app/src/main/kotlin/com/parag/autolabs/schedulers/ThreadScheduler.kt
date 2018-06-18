package com.parag.autolabs.schedulers

import io.reactivex.ObservableTransformer

interface ThreadScheduler {
    fun <T> allocateSchedule(): ObservableTransformer<T, T>
}