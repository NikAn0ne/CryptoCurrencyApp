package com.example.data.storage.local

import android.content.Context
import com.example.data.util.toDomain
import com.example.data.util.toEntity
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataLocalRepository

class MarketDataLocalRepositoryImpl(context: Context): MarketDataLocalRepository {
    private val db = CryptoCurrencyDatabase.initDb(context)

    override suspend fun upsert(cryptoCurrencyData: CryptoCurrencyData) {

        return db.getDao().upsertCrypto(cryptoCurrencyData.toEntity())
    }

    override suspend fun getCryptoData(): MutableList<CryptoCurrencyData> {
        return db.getDao().getCryptoData().map { it.toDomain() }.toMutableList()
    }

    override suspend fun deleteCryptoData(cryptoCurrencyData: CryptoCurrencyData) {
        db.getDao().deleteCryptoData(cryptoCurrencyData.toEntity())
    }
}