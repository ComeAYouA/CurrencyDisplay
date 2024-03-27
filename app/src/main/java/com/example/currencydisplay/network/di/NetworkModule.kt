package com.example.currencydisplay.network.di

import com.example.currencydisplay.network.CurrencyApi
import com.example.currencydisplay.network.retrofit.CurrencyApiImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindCurrencyApiImpl_to_CurrencyApi(input: CurrencyApiImpl): CurrencyApi

    companion object {

        @Provides
        fun providesRetrofit(): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl("https://www.cbr-xml-daily.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}