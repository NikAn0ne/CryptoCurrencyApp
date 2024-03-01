package com.example.data.storage.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cryptoCurrency")
class CryptoCurrencyEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val symbol: String,
    val totalSupply: Double,
    val quoteDominance: Double,
    val quoteMarketCap: Double,
    val percentChange1h: Double,
    val percentChange1y: Double,
    val percentChange24h: Double,
    val percentChange30d: Double,
    val percentChange60d: Double,
    val percentChange7d: Double,
    val percentChange90d: Double,
    val price: Double,
    val volume24h: Double

)