package com.example.domain.repository

import com.example.domain.model.CryptoCurrency

interface MarketDataRepository {
    suspend fun getMarketData(): MutableList<CryptoCurrency>?
}