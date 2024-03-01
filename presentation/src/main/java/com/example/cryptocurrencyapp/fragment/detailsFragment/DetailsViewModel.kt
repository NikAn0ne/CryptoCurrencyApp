package com.example.cryptocurrencyapp.fragment.detailsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataLocalRepository
import com.example.domain.repository.MarketDataRepository
import com.example.domain.useCases.GetWatchListUseCase
import com.example.domain.useCases.ReadWatchListUseCase
import com.example.domain.useCases.StoreWatchListUseCase
import com.example.domain.useCases.WatchListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(repository: MarketDataRepository,localRepository: MarketDataLocalRepository) : ViewModel(){


   private val readWatchListUseCase = ReadWatchListUseCase(repository)

   private val getWatchListUseCase = GetWatchListUseCase(repository)

   private val storeWatchListUseCase = StoreWatchListUseCase(repository)

    private val watchListUseCase = WatchListUseCase(localRepository)


    private val _watchList = MutableLiveData<ArrayList<String>>()

    val watchList : MutableLiveData<MutableList<CryptoCurrencyData>> = MutableLiveData<MutableList<CryptoCurrencyData>>()

     fun readWatchList() {
        readWatchListUseCase.readWatchList()
    }

    fun getWatchList(): ArrayList<String> {
        readWatchList()
        val result = getWatchListUseCase.getWatchList()!!
        _watchList.value = getWatchListUseCase.getWatchList()!!
        return result
    }

    fun addToWatchList(cryptoData: CryptoCurrencyData) = viewModelScope.launch{

            watchListUseCase.addToWatchList(cryptoData)

    }

    fun getLocalWatchList() = viewModelScope.launch(Dispatchers.IO){

            watchList.postValue(watchListUseCase.getWatchList())




    }

    fun deleteWatchList(cryptoData: CryptoCurrencyData) = viewModelScope.launch{

            watchListUseCase.deleteItem(cryptoData)


    }

}