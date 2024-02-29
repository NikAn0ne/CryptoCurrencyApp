package com.example.domain.useCases

import com.example.domain.repository.MarketDataRepository

class GetWatchListUseCase(private val marketDataRepository: MarketDataRepository) {

    fun getWatchList() : ArrayList<String>?{
        return marketDataRepository.getWatchList()
    }

}