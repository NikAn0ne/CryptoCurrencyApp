package com.example.domain.repository

import com.example.domain.model.CryptoCurrencyData

interface MarketDataLocalRepository {
    suspend fun upsert(cryptoCurrencyData: CryptoCurrencyData)

    suspend fun getCryptoData(): MutableList<CryptoCurrencyData>

    suspend fun deleteCryptoData(cryptoCurrencyData: CryptoCurrencyData)
}