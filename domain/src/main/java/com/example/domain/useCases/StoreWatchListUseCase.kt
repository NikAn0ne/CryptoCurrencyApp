package com.example.domain.useCases

import com.example.domain.repository.MarketDataRepository

class StoreWatchListUseCase(private val marketDataRepository: MarketDataRepository) {

    fun storeWatchList(){
        return marketDataRepository.saveSharedPrefData()
    }

}