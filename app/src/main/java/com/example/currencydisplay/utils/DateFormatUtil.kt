package com.example.currencydisplay.utils

object DateFormatUtil {
    fun Int.toTwoDigitsFormat(): String {
        val str = this.toString()
            return when{
                str.length < 2 -> "0$str"
                else -> str
            }
        }
}