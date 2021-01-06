package com.fchps.rvtechnicaltest.utils

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun Disposable.store(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)

fun <T> ioToMainThreadObservableTransformer() =
    ObservableTransformer<T, T> { observable ->
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


fun ioToMainThreadCompletableTransformer() = CompletableTransformer { observable ->
    observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}