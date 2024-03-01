package com.example.cryptocurrencyapp.fragment.marketFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.useCases.GetMarketDataUseCase
import kotlinx.coroutines.launch

class MarketViewModel(getMarketDataUseCase : GetMarketDataUseCase): ViewModel() {

    private val mutableList = MutableLiveData<List<CryptoCurrencyData>>()
    val list : LiveData<List<CryptoCurrencyData>> = mutableList



    init {
        getList(getMarketDataUseCase)
    }

    fun getList(getMarketDataUseCase: GetMarketDataUseCase){
        viewModelScope.launch {
            mutableList.value = getMarketDataUseCase.getMarketData()
        }
    }


}