package com.carlos.tragosapp.remote

import com.carlos.tragosapp.data.model.Drink
import com.carlos.tragosapp.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService  {

    @GET("search.php")
    suspend fun getDrinksByName(@Query("s") drinkName: String) : DrinkList





}