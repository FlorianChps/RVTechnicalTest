package com.fchps.rvtechnicaltest.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableTransformer
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

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