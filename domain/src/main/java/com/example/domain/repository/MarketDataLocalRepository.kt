package com.example.domain.repository

import com.example.domain.model.CryptoCurrencyData

interface MarketDataLocalRepository {
    fun upsert(cryptoCurrencyData: CryptoCurrencyData)

    fun getCryptoData(): MutableList<CryptoCurrencyData>

    fun deleteCryptoData(cryptoCurrencyData: CryptoCurrencyData)
}