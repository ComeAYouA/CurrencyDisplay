package com.example.currencydisplay.network.model

import com.google.gson.annotations.SerializedName

data class Exchange(
    @SerializedName("ID")
    val id: String,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Value")
    val currentValue: Double,

)
