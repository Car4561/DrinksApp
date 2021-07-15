package com.carlos.tragosapp.domain

import com.carlos.tragosapp.cache.LocalDataSource
import com.carlos.tragosapp.domain.models.Drink
import com.carlos.tragosapp.domain.models.DrinkEntity
import com.carlos.tragosapp.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(private val localdataSource: LocalDataSource, private val remoteDatSource: RemoteDataSource) : Repository {

    override fun getDrinksList(drinkName: String): Flow<List<Drink>> = flow {
        emit(remoteDatSource.getDrinksByName(drinkName))
    }

    override fun getDrinksFavorites(): Flow<List<Drink>> = localdataSource.getDrinksFavorites()

    override suspend fun insertDrink(drink: Drink) {
        localdataSource.insertDrinkIntoRoom(drink)
    }

    override suspend fun deleteDrink(drink: Drink) {
        localdataSource.deleteDrinkInRoom(drink)
    }


}

