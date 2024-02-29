package com.example.domain.repository

import com.example.domain.model.CryptoCurrencyData

interface MarketDataRepository {
    suspend fun getMarketData(): MutableList<CryptoCurrencyData>?

    fun saveSharedPrefData()

    fun readSharedPrefData()

    fun getWatchList() : ArrayList<String>?
}