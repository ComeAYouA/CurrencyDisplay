package com.example.currencydisplay.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencydisplay.model.ExchangeRates
import com.example.currencydisplay.network.model.errorDescription
import com.example.currencydisplay.network.retrofit.CurrencyApiImpl
import com.example.currencydisplay.utils.DateFormatUtil.toTwoDigitsFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyApi: CurrencyApiImpl
): ViewModel() {

    val screenState: MutableStateFlow<CurrencyScreenState> = MutableStateFlow(CurrencyScreenState.Loading)
    private val time = Calendar.getInstance()

    fun load() {
        viewModelScope.launch {

            screenState.update {
                CurrencyScreenState.Loading
            }


            try {
                val request = currencyApi.getExchangeRates()

                time.timeInMillis = System.currentTimeMillis()

                val day = time.get(Calendar.DAY_OF_MONTH).toTwoDigitsFormat()
                val month = time.get(Calendar.MONTH).toTwoDigitsFormat()
                val year = time.get(Calendar.YEAR)

                val hour = time.get(Calendar.HOUR_OF_DAY).toTwoDigitsFormat()
                val minute = time.get(Calendar.MINUTE).toTwoDigitsFormat()
                val second = time.get(Calendar.SECOND).toTwoDigitsFormat()

                screenState.update {
                    CurrencyScreenState.Success(
                        ExchangeRates(
                            date = "$day/$month/$year $hour:$minute:$second",
                            exchangeRate = request.exchangeRate
                        )
                    )
                }
            }
            catch (e: Exception){
                screenState.update {
                    CurrencyScreenState.Error(
                        "Невозможно обновить список валют: " + e.errorDescription
                    )
                }

            }
        }
    }

    suspend fun setupSync(){
        coroutineScope {
            while (isActive){
                load()

                delay(30000)
            }
        }
    }



}