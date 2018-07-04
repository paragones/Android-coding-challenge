package com.parag.autolabs

import com.parag.autolabs.schedulers.ThreadScheduler
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers

class TestScheduler() : ThreadScheduler {
    override fun <T> allocateSchedule(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.observeOn(Schedulers.trampoline())
                    .subscribeOn(Schedulers.trampoline())
        }
    }

}