package com.example.cryptocurrencyapp.fragment.homeFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.adapter.TopMarketAdapter
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.useCases.GetMarketDataUseCase
import kotlinx.coroutines.launch

class HomeViewModel(getMarketDataUseCase: GetMarketDataUseCase) : ViewModel() {

    private val mutableList = MutableLiveData<List<CryptoCurrencyData>>()
    val list : LiveData<List<CryptoCurrencyData>> = mutableList

    init {
        getList(getMarketDataUseCase)
        Log.d("vmList", list.value.toString())
    }


     fun getList(getMarketDataUseCase: GetMarketDataUseCase){

        viewModelScope.launch {
        mutableList.value = getMarketDataUseCase.getMarketData()

    }
    }

}