package com.example.currencydisplay.screen.rv

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.currencydisplay.R
import com.example.currencydisplay.network.model.Exchange

class CurrencyViewHolder(private val view: View): ViewHolder(view) {

    fun bind(exchange: Exchange){
        val currencyCodeTextView = view.findViewById<TextView>(R.id.currency_code_text_view)
        val exchangeRateTextView = view.findViewById<TextView>(R.id.exchange_value_text_view)
        val currencyNameTextView = view.findViewById<TextView>(R.id.currency_name_text_view)

        currencyCodeTextView.text = exchange.charCode
        currencyNameTextView.text = "${exchange.nominal} ${exchange.name}\n= "
        exchangeRateTextView.text = exchange.currentValue.toString() + " руб."
    }
}