package com.fchps.rvtechnicaltest.data.repository

import com.fchps.rvtechnicaltest.ui.features.station.PlaceModel
import io.reactivex.Completable
import io.reactivex.Observable

interface IPlaceRepository {

    fun getPlaces(searched: String): Observable<List<PlaceModel>>

    fun savePlace(placeModel: PlaceModel): Completable

    fun getLocalPlaces(): Observable<List<PlaceModel>>

    fun deletePlace(placeModel: PlaceModel): Completable
}