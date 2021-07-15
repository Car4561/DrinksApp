package com.carlos.tragosapp.domain

import com.carlos.tragosapp.domain.models.Drink
import com.carlos.tragosapp.domain.models.DrinkEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getDrinksList(drinkName : String): Flow<List<Drink>>
    fun getDrinksFavorites(): Flow<List<Drink>>
    suspend fun insertDrink(drink: Drink)
    suspend fun deleteDrink(drink: Drink)
}