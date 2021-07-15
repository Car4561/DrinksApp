package com.carlos.tragosapp.domain.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val drinkId: String,
    @SerializedName("strDrinkThumb")
    val imagen: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strInstructions")
    val description: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol: String = ""
) : Parcelable



@Entity(tableName = "tb_drink")
data class DrinkEntity(
    @PrimaryKey
    val drinkId: String,
    @ColumnInfo(name = "image_url")
    val imagen: String = "",
    @ColumnInfo(name = "name")
    val nombre: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "has_alcohol")
    val hasAlcohol: String = ""
)