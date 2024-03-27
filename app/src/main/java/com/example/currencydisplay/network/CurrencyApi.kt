package com.example.currencydisplay.network

import com.example.currencydisplay.network.model.ExchangeRatesNetworkResource
import retrofit2.http.GET

interface CurrencyApi {

    @GET("daily_json.js")
    suspend fun getExchangeRates(): ExchangeRatesNetworkResource

}