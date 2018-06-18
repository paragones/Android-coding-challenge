package com.parag.autolabs.ui.base

import com.parag.autolabs.schedulers.ThreadScheduler
import io.reactivex.ObservableTransformer

abstract class BasePresenter<T>(protected var scheduler: ThreadScheduler) {
    protected var view: T? = null

    fun attach(view: T) {
        this.view = view
    }

    fun detach() {
        this.view = null
    }

    protected fun <T> allocateSchedule(): ObservableTransformer<T, T> {
        return scheduler.allocateSchedule<T>()
    }
}