package com.example.data.storage.models

import com.example.data.storage.models.CryptoCurrency


data class Data(
    val cryptoCurrencyList: MutableList<CryptoCurrency>,
    val totalCount: String
)