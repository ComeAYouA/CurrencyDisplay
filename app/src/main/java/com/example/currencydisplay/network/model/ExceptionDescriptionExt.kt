package com.example.currencydisplay.network.model


import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

val Exception.errorDescription: String
    get() =
        when(this) {
            is UnknownHostException -> "отсутствует подключение к интернету"
            is SocketTimeoutException -> "сервер не отвечает"
            is ConnectException -> "сервер не найден"
            else -> "неизвестная ошибка"
        }
