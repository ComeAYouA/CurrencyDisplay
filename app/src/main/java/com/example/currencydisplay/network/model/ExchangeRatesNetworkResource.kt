package com.example.currencydisplay.network.model

import com.google.gson.annotations.SerializedName

data class ExchangeRatesNetworkResource(
    @SerializedName("Valute")
    val exchangeRate: Map<Currency, Exchange>
)
