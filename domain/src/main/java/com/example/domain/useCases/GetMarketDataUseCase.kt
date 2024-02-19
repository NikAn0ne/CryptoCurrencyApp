package com.example.domain.useCases

import com.example.domain.model.CryptoCurrency
import com.example.domain.repository.MarketDataRepository

class GetMarketDataUseCase(
    private val marketDataRep: MarketDataRepository
) {

    suspend fun getMarketData(): List<CryptoCurrency>? {
        val marketData = marketDataRep.getMarketData()

        if (marketData.isNullOrEmpty()){
            return null
        }

        return marketData
    }
}