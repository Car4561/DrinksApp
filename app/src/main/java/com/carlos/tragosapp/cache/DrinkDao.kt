package com.carlos.tragosapp.cache

import androidx.room.*
import com.carlos.tragosapp.domain.models.DrinkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkDao {

    @Query("SELECT * FROM tb_drink")
    fun getAllFavoriteDrinks(): Flow<List<DrinkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(drink: DrinkEntity)

    @Delete
    suspend fun deleteDrinkFavorite(drink: DrinkEntity)

}