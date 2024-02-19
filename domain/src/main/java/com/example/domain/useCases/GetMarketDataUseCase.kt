package com.example.domain.useCases

import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataRepository

class GetMarketDataUseCase(
    private val marketDataRep: MarketDataRepository
) {

    suspend fun getMarketData(): List<CryptoCurrencyData>? {
        val marketData = marketDataRep.getMarketData()

        if (marketData.isNullOrEmpty()){
            return null
        }

        return marketData
    }
}