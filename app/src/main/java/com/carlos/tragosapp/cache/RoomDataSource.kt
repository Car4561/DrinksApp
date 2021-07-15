package com.carlos.tragosapp.cache

import com.carlos.tragosapp.data.toDomainDrink
import com.carlos.tragosapp.domain.models.Drink
import com.carlos.tragosapp.data.toEntityDrink
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.w3c.dom.Entity

class RoomDataSource(private val drinkDatabase: DrinkDatabase) : LocalDataSource {

    private val drinkDao = drinkDatabase.drinkDao()

    override fun getDrinksFavorites(): Flow<List<Drink>> =
        drinkDao.getAllFavoriteDrinks().map { drinkFavoritesList ->
            drinkFavoritesList.map {
                it.toDomainDrink()
            }
        }


    override suspend fun insertDrinkIntoRoom(drink: Drink) {
        drinkDao.insertFavorite(drink = drink.toEntityDrink())
    }

    override suspend fun deleteDrinkInRoom(drink: Drink) {
        drinkDao.deleteDrinkFavorite(drink = drink.toEntityDrink())
    }

}