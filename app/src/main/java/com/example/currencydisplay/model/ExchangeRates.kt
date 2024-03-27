package com.example.currencydisplay.model

import com.example.currencydisplay.network.model.Currency
import com.example.currencydisplay.network.model.Exchange

data class ExchangeRates(
    val date: String,
    val exchangeRate: Map<Currency, Exchange>
)