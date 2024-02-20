package com.example.cryptocurrencyapp.fragment.homeFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.adapter.TopMarketAdapter
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.useCases.GetMarketDataUseCase
import kotlinx.coroutines.launch

class HomeViewModel(getMarketDataUseCase: GetMarketDataUseCase) : ViewModel() {

    val list = MutableLiveData<List<CryptoCurrencyData>>()


    fun getList(getMarketDataUseCase: GetMarketDataUseCase){

        viewModelScope.launch {
        list.value = getMarketDataUseCase.getMarketData()

    }
    }

}