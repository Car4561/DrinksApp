package com.carlos.tragosapp.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carlos.tragosapp.domain.models.DrinkEntity

@Database(
    entities = [DrinkEntity::class],
    version = 2
)
abstract class DrinkDatabase : RoomDatabase() {

    abstract fun drinkDao(): DrinkDao

    companion object {

        private var INSTANCE: DrinkDatabase? = null

        fun getInstance(context: Context): DrinkDatabase =
            INSTANCE ?: synchronized(this) {
                buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DrinkDatabase::class.java,
            "drinkDB"
        ).fallbackToDestructiveMigration()
            .build()

        fun destroyInstance() {
            INSTANCE = null
        }

    }

}