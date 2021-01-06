package com.fchps.rvtechnicaltest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fchps.rvtechnicaltest.ui.features.station.PlaceModel

@Database(entities = [PlaceModel::class], version = 1, exportSchema = true)
abstract class PlaceDatabase : RoomDatabase() {

    abstract fun placesDatabaseDao(): PlaceDataBaseDao

    companion object {

        @Volatile
        private var INSTANCE: PlaceDatabase? = null

        fun getDatabase(context: Context): PlaceDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: createDatabase(context).also { INSTANCE = it }
            }

        private fun createDatabase(context: Context): PlaceDatabase {
            return Room.databaseBuilder(context, PlaceDatabase::class.java, "favorite_places_db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}