package com.example.data.models

data class CryptoCurrencyResponseItem (
    val id: Int,
    val name: String,
    val quotes: List<Quote>,
    val symbol: String,
    val totalSupply: Double
)
