package com.example.data.repository

import android.util.Log
import com.example.data.API.ApiInterface
import com.example.data.models.Data
import com.example.data.util.toDomain
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataRepository
import java.lang.RuntimeException

class MarketDataRepositoryImpl(private val api: ApiInterface): MarketDataRepository {
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
}