package com.example.domain.useCases

import com.example.domain.repository.MarketDataRepository

class WatchListUseCase(private val marketDataRepository: MarketDataRepository) {

    fun readWatchList(){
        return marketDataRepository.readSharedPrefData()
    }

    fun storeWatchList(){
        return marketDataRepository.saveSharedPrefData()
    }

     fun getWatchList() : ArrayList<String>?{
        return marketDataRepository.getWatchList()
    }
}