package com.carlos.tragosapp.cache

import com.carlos.tragosapp.domain.models.Drink
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getDrinksFavorites(): Flow<List<Drink>>
    suspend fun insertDrinkIntoRoom(drink: Drink)
    suspend fun deleteDrinkInRoom(drink: Drink)
}