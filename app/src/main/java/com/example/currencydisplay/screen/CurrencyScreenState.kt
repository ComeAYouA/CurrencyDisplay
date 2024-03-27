package com.example.currencydisplay.screen

import com.example.currencydisplay.model.ExchangeRates

sealed interface CurrencyScreenState {
    object Loading : CurrencyScreenState

    data class Success(
        val data: ExchangeRates
    ) : CurrencyScreenState

    data class Error(
        val message: String
    ) : CurrencyScreenState
}