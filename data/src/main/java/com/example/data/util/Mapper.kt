package com.example.data.util

import com.example.data.storage.local.CryptoCurrencyEntity
import com.example.data.storage.models.CryptoCurrency
import com.example.data.storage.models.Data
import com.example.domain.model.CryptoCurrencyData

fun CryptoCurrency.toDomain() : CryptoCurrencyData{
        return CryptoCurrencyData(
                id = this.id,
                name = this.name,
                symbol = this.symbol,
                totalSupply = this.totalSupply,
                quoteDominance = this.quotes[0].dominance,
                quoteMarketCap = this.quotes[0].marketCap,
                percentChange1h = this.quotes[0].percentChange1h,
                percentChange24h = this.quotes[0].percentChange24h,
                percentChange7d = this.quotes[0].percentChange7d,
                percentChange30d = this.quotes[0].percentChange30d,
                percentChange60d = this.quotes[0].percentChange60d,
                percentChange90d = this.quotes[0].percentChange90d,
                percentChange1y = this.quotes[0].percentChange1y,
                price = this.quotes[0].price,
                volume24h = this.quotes[0].volume24h)
}

fun CryptoCurrencyEntity.toDomain(): CryptoCurrencyData{
        return CryptoCurrencyData(
                id = this.id,
                name = this.name,
                symbol = this.symbol,
                totalSupply = this.totalSupply,
                quoteDominance = this.quoteDominance,
                quoteMarketCap = this.quoteMarketCap,
                percentChange1h = this.percentChange1h,
                percentChange24h = this.percentChange24h,
                percentChange7d = this.percentChange7d,
                percentChange30d = this.percentChange30d,
                percentChange60d = this.percentChange60d,
                percentChange90d = this.percentChange90d,
                percentChange1y = this.percentChange1y,
                price = this.price,
                volume24h = this.volume24h

        )
}

fun CryptoCurrencyData.toEntity() : CryptoCurrencyEntity{
        return CryptoCurrencyEntity(
                id = this.id,
                name = this.name,
                symbol = this.symbol,
                totalSupply = this.totalSupply,
                quoteDominance = this.quoteDominance,
                quoteMarketCap = this.quoteMarketCap,
                percentChange1h = this.percentChange1h,
                percentChange24h = this.percentChange24h,
                percentChange7d = this.percentChange7d,
                percentChange30d = this.percentChange30d,
                percentChange60d = this.percentChange60d,
                percentChange90d = this.percentChange90d,
                percentChange1y = this.percentChange1y,
                price = this.price,
                volume24h = this.volume24h
        )
}







