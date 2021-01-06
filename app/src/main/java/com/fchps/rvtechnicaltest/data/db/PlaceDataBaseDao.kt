package com.fchps.rvtechnicaltest.data.db

import androidx.room.*
import com.fchps.rvtechnicaltest.ui.features.station.PlaceModel
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PlaceDataBaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlace(placeModel: PlaceModel): Completable

    @Delete
    fun deletePlace(placeModel: PlaceModel): Completable

    @Query("SELECT * FROM place_favorite_table")
    fun getFavoritesPlaces(): Observable<List<PlaceModel>>
}