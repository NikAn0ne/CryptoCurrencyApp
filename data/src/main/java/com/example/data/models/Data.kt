package com.example.data.models

import com.example.data.models.CryptoCurrency


data class Data(
    val cryptoCurrencyList: MutableList<CryptoCurrency>,
    val totalCount: String
)