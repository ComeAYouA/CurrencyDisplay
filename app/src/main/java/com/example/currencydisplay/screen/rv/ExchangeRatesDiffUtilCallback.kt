package com.example.currencydisplay.screen.rv

import androidx.recyclerview.widget.DiffUtil
import com.example.currencydisplay.network.model.Exchange

class ExchangeRatesDiffUtilCallback(
    private val oldList: List<Exchange>,
    private val newList: List<Exchange>
): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].currentValue == newList[newItemPosition].currentValue
    }

}