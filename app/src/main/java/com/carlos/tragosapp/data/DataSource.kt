package com.carlos.tragosapp.data

import com.carlos.tragosapp.domain.models.Drink
import com.carlos.tragosapp.vo.Resource
import com.carlos.tragosapp.vo.RetrofitClient


class DataSource {

    suspend fun getDrinksByName(drinkName: String): Resource.Sucess<List<Drink>> {
        return Resource.Sucess(RetrofitClient.webservice.getDrinksByName(drinkName).drinkList ?: emptyList())
    }

    private val genereteDrinkList = Resource.Sucess(
        listOf(
            Drink(
                "https://images.absolutdrinks.com/drink-images/Raw/Absolut/c8bc404c-fc30-4ec8-a9a5-0dca3913bbbb.jpg?imwidth=500",
                "Margarita",
                "Con azucar, vodka y nueces"
            ),
            Drink(
                "https://www.recetas-argentinas.com/base/stock/Recipe/2-image/2-image_web.jpg",
                "Fernet",
                "Fernet con coca y 2 hielos)"
            ),
            Drink(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzIHHNxk39fsVCAMtP7Z2m0EKykQ7Xmj03yw&usqp=CAU",
                "Toro",
                "Toro con pritty"
            ),
            Drink(
                "https://i1.tagstat.com/p1/p/uXPwegCCzMH--a9A8BI-toysnTYu99JunTiV43v-4MINofxJoK1kdzv2HqfSN_xQ.jpg",
                "Gancia",
                "Gancia con sprite"
            )
        )
    )

    fun getTragosList(): Resource<List<Drink>> {
        return genereteDrinkList
    }

}