package com.example.cryptocurrencyapp.fragment.homeFragment


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.useCases.GetMarketDataUseCase
import kotlinx.coroutines.launch

class HomeViewModel(getMarketDataUseCase: GetMarketDataUseCase) : ViewModel() {

    val getMarketDataUseCase = getMarketDataUseCase

    private val mutableList = MutableLiveData<List<CryptoCurrencyData>>()
    val list : LiveData<List<CryptoCurrencyData>> = mutableList

    init {
        getList()
        //Log.d("vmList", list.value.toString())
    }


     fun getList(){

        viewModelScope.launch {
        mutableList.value = getMarketDataUseCase.getMarketData()
    }
    }



}