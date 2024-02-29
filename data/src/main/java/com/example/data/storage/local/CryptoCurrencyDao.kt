package com.example.data.storage.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CryptoCurrencyDao {

    @Upsert
    fun upsertCrypto(cryptoCurrency : CryptoCurrencyEntity)

    @Query("SELECT * FROM cryptoCurrency")
    fun getCryptoData() : List<CryptoCurrencyEntity>

    @Delete
    fun deleteCryptoData(cryptoData: CryptoCurrencyEntity)
}