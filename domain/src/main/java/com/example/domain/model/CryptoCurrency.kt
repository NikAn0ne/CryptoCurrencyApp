package com.example.domain.model

import java.io.Serializable

data class CryptoCurrency(
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



)   : Serializable{
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}