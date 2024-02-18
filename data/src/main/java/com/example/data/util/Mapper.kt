package com.example.data.util

import com.example.data.models.CryptoCurrencyResponseItem
import com.example.domain.model.CryptoCurrency

fun CryptoCurrencyResponseItem.toDomain() : CryptoCurrency{
    return CryptoCurrency(
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
        volume24h = this.quotes[0].volume24h



    )
}