package com.example.cryptocurrencyapp.fragment.topLossGainFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataRepository
import com.example.domain.useCases.GetMarketDataUseCase
import kotlinx.coroutines.launch


class TopLossGainViewModel(repository: MarketDataRepository): ViewModel() {

    //TODO: implement the correct logic

    private val _marketData = MutableLiveData<List<CryptoCurrencyData>?>()
    val marketData: LiveData<List<CryptoCurrencyData>?> = _marketData

    val getMarketDataUseCase = GetMarketDataUseCase(repository)



    init {
        getMarketData()
        Log.d("vmList",_marketData.value.toString())
    }


    fun getMarketData(){
        viewModelScope.launch {
            val result = getMarketDataUseCase.getMarketData()
            _marketData.value = result

        }
    }


}