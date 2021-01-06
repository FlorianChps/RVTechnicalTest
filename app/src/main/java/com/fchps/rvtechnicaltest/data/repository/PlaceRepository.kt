package com.fchps.rvtechnicaltest.data.repository

import com.fchps.rvtechnicaltest.data.entities.Place
import com.fchps.rvtechnicaltest.data.local.PlaceLocalDataSource
import com.fchps.rvtechnicaltest.data.remote.PlaceRemoteDataSource
import com.fchps.rvtechnicaltest.ui.features.station.PlaceModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PlaceRepository @Inject constructor(
    private val remoteDataSource: PlaceRemoteDataSource,
    private val localDataSource: PlaceLocalDataSource
) : IPlaceRepository {

    override fun getPlaces(searched: String): Observable<List<PlaceModel>> {
        return Observable.zip(
            localDataSource.getPlaces(),
            remoteDataSource.getPlaces(searched).map { result ->
                result.places.map { mapPlaceToPlaceModel(it) }
            },
            { localPlaces, remotePlaces ->
                if (localPlaces.isNullOrEmpty().not()) {
                    val finalList: List<PlaceModel> =
                        filterListWithFavorites(remotePlaces, localPlaces)
                    localPlaces + finalList
                } else {
                    remotePlaces
                }
            })
    }

    override fun savePlace(placeModel: PlaceModel) = localDataSource.savePlace(placeModel)

    override fun deletePlace(placeModel: PlaceModel) = localDataSource.removePlace(placeModel)

    override fun getLocalPlaces(): Observable<List<PlaceModel>> = localDataSource.getPlaces()

    private fun filterListWithFavorites(
        remotePlaces: List<PlaceModel>,
        localPlaces: List<PlaceModel>
    ): List<PlaceModel> {
        localPlaces.map { localModel -> localModel.id }.also { idList ->
            return remotePlaces.filter { remoteModel -> idList.contains(remoteModel.id).not() }
        }
    }

    private fun mapPlaceToPlaceModel(place: Place) = PlaceModel(place.id, place.name, false)
}