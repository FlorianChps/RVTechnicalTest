package com.fchps.rvtechnicaltest.data.repository

import com.fchps.rvtechnicaltest.ui.features.station.PlaceModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface IPlaceRepository {

    fun getPlaces(searched: String): Observable<List<PlaceModel>>

    fun savePlace(placeModel: PlaceModel): Completable

    fun getLocalPlaces(): Observable<List<PlaceModel>>

    fun deletePlace(placeModel: PlaceModel): Completable
}