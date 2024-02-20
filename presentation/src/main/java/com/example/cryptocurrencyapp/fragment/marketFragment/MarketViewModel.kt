package com.example.cryptocurrencyapp.fragment.marketFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataRepository
import com.example.domain.useCases.GetMarketDataUseCase
import kotlinx.coroutines.launch
import java.util.ArrayList
import java.util.Locale

class MarketViewModel(getMarketDataUseCase : GetMarketDataUseCase): ViewModel() {

    private val mutableList = MutableLiveData<List<CryptoCurrencyData>>()
    val list : LiveData<List<CryptoCurrencyData>> = mutableList
    val adapterList = listOf<List<CryptoCurrencyData>>()
    val _data = MutableLiveData<ArrayList<CryptoCurrencyData>>()



    init {
        getList(getMarketDataUseCase)
    }

    fun getList(getMarketDataUseCase: GetMarketDataUseCase){
        viewModelScope.launch {
            mutableList.value = getMarketDataUseCase.getMarketData()
        }
    }

    /*fun searchCoin(){



    }*/




  /*  fun updateData(searchText: String, list: List<CryptoCurrencyData>){
        var data = ArrayList<CryptoCurrencyData>()
        Log.d("vlw", list.toString())
        for (item in list){
            val coinName = item.name.lowercase(Locale.getDefault())
            val coinSymbol = item.symbol.lowercase(Locale.getDefault())

            if (coinName.contains(searchText) || coinSymbol.contains(searchText)){
                _data.value?.add(item)
            }
         }
        data = _data.value!!

    }*/
}