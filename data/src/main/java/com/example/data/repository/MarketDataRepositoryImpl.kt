package com.example.data.repository

import android.util.Log
import com.example.data.API.ApiInterface
import com.example.data.storage.WatchListStorage
import com.example.data.storage.models.Data
import com.example.data.util.toDomain
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataRepository
import java.lang.RuntimeException

class MarketDataRepositoryImpl(private val api: ApiInterface,private val watchListStorage : WatchListStorage): MarketDataRepository {
    override suspend fun getMarketData(): MutableList<CryptoCurrencyData>? {
        return runCatching {
            val resp = api.getMarketData()
            if (resp.isSuccessful){
                resp.body()?.data?.cryptoCurrencyList?.map {
                    it.toDomain()
                }?.toMutableList()

            }else{
                throw RuntimeException("Sorry, there is a problem with data")
            }

        }.getOrElse {
            return null
        }
    }

    override fun saveSharedPrefData() {
        return watchListStorage.saveData()
    }

    override fun readSharedPrefData() {
        return watchListStorage.readData()
    }

    override fun getWatchList(): ArrayList<String>? {
        return watchListStorage.getWatchList()
    }
}