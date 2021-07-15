package com.carlos.tragosapp.remote.models

import android.os.Parcelable
import com.carlos.tragosapp.domain.models.Drink
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DrinkResponseModel(
    @SerializedName("drinks")
    val drinkList: List<Drink>,
) : Parcelable
