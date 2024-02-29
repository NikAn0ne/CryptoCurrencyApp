package com.example.domain.useCases

import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataLocalRepository

class WatchListUseCase(private val localRepository: MarketDataLocalRepository) {
    fun getWatchList(): MutableList<CryptoCurrencyData>{
        return localRepository.getCryptoData()
    }
    fun deleteItem(cryptoData: CryptoCurrencyData){
        localRepository.deleteCryptoData(cryptoData)
    }

    fun addToWatchList(cryptoData: CryptoCurrencyData){
        localRepository.upsert(cryptoData)
    }

}