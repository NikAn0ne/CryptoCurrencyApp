package com.example.data.storage.local

import com.example.data.util.toDomain
import com.example.data.util.toEntity
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataLocalRepository

class MarketDataLocalRepositoryImpl(private val dao: CryptoCurrencyDao): MarketDataLocalRepository {
    override fun upsert(cryptoCurrencyData: CryptoCurrencyData) {
        return dao.upsertCrypto(cryptoCurrencyData.toEntity())
    }

    override fun getCryptoData(): MutableList<CryptoCurrencyData> {
        return dao.getCryptoData().map { it.toDomain() }.toMutableList()
    }

    override fun deleteCryptoData(cryptoCurrencyData: CryptoCurrencyData) {
        dao.deleteCryptoData(cryptoCurrencyData.toEntity())
    }
}