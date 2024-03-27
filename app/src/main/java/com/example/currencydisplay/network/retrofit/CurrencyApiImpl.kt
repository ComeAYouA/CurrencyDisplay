package com.example.currencydisplay.network.retrofit

import com.example.currencydisplay.network.CurrencyApi
import com.example.currencydisplay.network.model.ExchangeRatesNetworkResource
import retrofit2.Retrofit
import javax.inject.Inject

class CurrencyApiImpl @Inject constructor(
    private val retrofit: Retrofit
): CurrencyApi {

    private val currencyApi = retrofit.create(CurrencyApi::class.java)
    override suspend fun getExchangeRates(): ExchangeRatesNetworkResource = currencyApi.getExchangeRates()
}