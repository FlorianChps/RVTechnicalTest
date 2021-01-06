package com.fchps.rvtechnicaltest.data.local

import com.fchps.rvtechnicaltest.data.db.PlaceDataBaseDao
import com.fchps.rvtechnicaltest.ui.features.station.PlaceModel
import io.reactivex.Observable
import javax.inject.Inject

class PlaceLocalDataSource @Inject constructor(
    private val placeDataBaseDao: PlaceDataBaseDao
) {

    fun savePlace(placeModel: PlaceModel) = placeDataBaseDao.insertPlace(placeModel)

    fun removePlace(placeModel: PlaceModel) = placeDataBaseDao.deletePlace(placeModel)

    fun getPlaces(): Observable<List<PlaceModel>> = placeDataBaseDao.getFavoritesPlaces()
}