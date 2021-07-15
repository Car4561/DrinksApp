package com.carlos.tragosapp.remote

import com.carlos.tragosapp.domain.models.Drink

interface RemoteDataSource {
    suspend fun getDrinksByName(drinkName: String): List<Drink>
}