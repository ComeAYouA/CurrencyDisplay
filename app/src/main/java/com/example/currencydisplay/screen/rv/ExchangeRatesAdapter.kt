package com.example.currencydisplay.screen.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.currencydisplay.R
import com.example.currencydisplay.network.model.Exchange
import javax.inject.Inject


class ExchangeRatesAdapter @Inject constructor(): Adapter<CurrencyViewHolder>() {

    private val exchangeRatesList: MutableList<Exchange> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_currency,
            parent,
            false
        )
        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int = exchangeRatesList.size


    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(exchangeRatesList[position])
    }

    fun setExchangeRatesList(data: List<Exchange>){

        val diffUtilCallback = ExchangeRatesDiffUtilCallback(exchangeRatesList, data)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)

        exchangeRatesList.clear()
        exchangeRatesList.addAll(data)

        diffUtilResult.dispatchUpdatesTo(this)
    }

}