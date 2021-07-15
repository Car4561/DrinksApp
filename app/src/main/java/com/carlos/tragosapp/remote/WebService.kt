package com.carlos.tragosapp.remote

import com.carlos.tragosapp.remote.models.DrinkResponseModel
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService  {

    @GET("search.php")
    suspend fun getDrinksByName(@Query(value = "s") drinkName: String) : DrinkResponseModel


}