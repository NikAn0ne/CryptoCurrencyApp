package com.example.domain.useCases

import com.example.domain.repository.MarketDataRepository

class ReadWatchListUseCase(private val marketDataRepository: MarketDataRepository) {

    fun readWatchList(){
        return marketDataRepository.readSharedPrefData()
    }

}