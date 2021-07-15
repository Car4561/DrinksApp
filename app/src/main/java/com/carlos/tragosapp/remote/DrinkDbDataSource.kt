package com.carlos.tragosapp.remote

import com.carlos.tragosapp.domain.models.Drink
import com.carlos.tragosapp.vo.RetrofitClient

class DrinkDbDataSource : RemoteDataSource {
    override suspend fun getDrinksByName(drinkName: String): List<Drink> {
        return RetrofitClient.webservice.getDrinksByName(drinkName).drinkList ?: emptyList()
    }
}