package com.example.data.repository

import com.example.data.API.ApiInterface
import com.example.data.util.toDomain
import com.example.domain.model.CryptoCurrency
import com.example.domain.repository.MarketDataRepository
import java.lang.RuntimeException

class MarketDataRepositoryImpl(private val api: ApiInterface): MarketDataRepository {
    override suspend fun getMarketData(): MutableList<CryptoCurrency>? {
        return runCatching {
            val res = api.getMarketData()
            if (res.isSuccessful){
                res.body()?.map {
                    it.toDomain()
                }?.toMutableList()
            }
            else {
                throw RuntimeException("Sorry, there is a problem to get the data")
            }
        }.getOrElse{
            return null
        }
    }
}