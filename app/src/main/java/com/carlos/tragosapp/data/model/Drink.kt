package com.carlos.tragosapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    @SerializedName("strDrinkThumb") val imagen: String = "",
    @SerializedName("strDrink") val nombre: String = "",
    @SerializedName("strInstructions")  val description: String = ""
):Parcelable

@Parcelize
data class DrinkList(
    @SerializedName("drinks") val drinkList: List<Drink>,
):Parcelable
