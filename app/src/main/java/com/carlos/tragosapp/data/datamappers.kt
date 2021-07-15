package com.carlos.tragosapp.data

import com.carlos.tragosapp.domain.models.Drink
import com.carlos.tragosapp.domain.models.DrinkEntity

fun Drink.toEntityDrink(): DrinkEntity =
    DrinkEntity(
        drinkId,
        imagen,
        nombre,
        description,
        hasAlcohol
    )

fun DrinkEntity.toDomainDrink(): Drink =
    Drink(
        drinkId,
        imagen,
        nombre,
        description,
        hasAlcohol
    )