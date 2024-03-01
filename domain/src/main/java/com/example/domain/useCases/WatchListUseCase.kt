package com.example.domain.useCases

import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataLocalRepository

class WatchListUseCase(private val localRepository: MarketDataLocalRepository) {
    suspend fun  getWatchList(): MutableList<CryptoCurrencyData>{
        return localRepository.getCryptoData()
    }
    suspend fun deleteItem(cryptoData: CryptoCurrencyData){
        localRepository.deleteCryptoData(cryptoData)
    }

    suspend fun addToWatchList(cryptoData: CryptoCurrencyData){
        localRepository.upsert(cryptoData)
    }

}